package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.BatteryFileConnector;
import com.bullimog.portal.connectors.CalibrationFileConnector;
import com.bullimog.portal.connectors.GravityFileConnector;
import com.bullimog.portal.connectors.TemperatureFileConnector;
import com.bullimog.portal.models.*;
import com.bullimog.portal.util.GravityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/brewery")
public class IspindelController {

    @Autowired //using config.ControllerDependencies
    TemperatureFileConnector tfc;

    @Autowired //using config.ControllerDependencies
    BatteryFileConnector bfc;

    @Autowired //using config.ControllerDependencies
    GravityFileConnector gfc;

    @Autowired //using config.ControllerDependencies
    CalibrationFileConnector cfc;

    static ISpindelMeta iSpindelMeta = new ISpindelMeta();

    IspindelController(){
        iSpindelMeta.setLastPost(LocalDateTime.now());
    }

    @RequestMapping(value = "/ispindel", method = RequestMethod.POST)
    public ResponseEntity<String> tester(@RequestBody ISpindelData isd) {
        GravityUtils gu = new GravityUtils(cfc.readCalibration());
        Temperatures t = tfc.readTemperatures();
        Double temperature = isd.getTemperature();
        t.appendTemperature(temperature);
        boolean temperatureWritten = tfc.writeTemperatures(t);

        Batteries b = bfc.readBatteries();
        b.appendBattery(isd.getBattery());
        boolean batteryWritten = bfc.writeBatteries(b);

        Gravities g = gfc.readGravities();
        Double tilt = isd.getAngle();
        Double calculatedGravity = gu.calculateGravity(tilt);
        Double adjustedGravity = gu.adjustGravityForTemperatureC(calculatedGravity, temperature);
        g.appendGravity(isd.getGravity(), adjustedGravity);
        boolean gravityWritten = gfc.writeGravities(g);

        if (temperatureWritten && batteryWritten && gravityWritten) {
            iSpindelMeta.setLastPost(LocalDateTime.now());
            return new ResponseEntity<String>("Done it!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Oops!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/temperatures")
    public Temperatures retrieveTemperatures() {
        ObjectMapper mapper = new ObjectMapper();
        Temperatures t = tfc.readTemperatures();
        return t;
    }

    @GetMapping("/batteries")
    public Batteries retrieveBatteries() {
        ObjectMapper mapper = new ObjectMapper();
        Batteries b = bfc.readBatteries();
        return b;
    }

    @GetMapping("/gravities")
    public Gravities retrieveGravities() {
        ObjectMapper mapper = new ObjectMapper();
        Gravities g = gfc.readGravities();
        return g;
    }

    @GetMapping("/ispindel-meta")
    public ISpindelMeta retrieveMeta() {
        return iSpindelMeta;
    }
}