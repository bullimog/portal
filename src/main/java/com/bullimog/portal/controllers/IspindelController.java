package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.BatteryFileConnector;
import com.bullimog.portal.connectors.BatteryFileConnectorImpl;
import com.bullimog.portal.connectors.GravityFileConnector;
import com.bullimog.portal.connectors.TemperatureFileConnector;
import com.bullimog.portal.models.Batteries;
import com.bullimog.portal.models.Gravities;
import com.bullimog.portal.models.ISpindelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bullimog.portal.models.Temperatures;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path="/brewery")
public class IspindelController {

    @Autowired //using config.ControllerDependencies
    TemperatureFileConnector tfc;

    @Autowired //using config.ControllerDependencies
    BatteryFileConnector bfc;

    @Autowired //using config.ControllerDependencies
    GravityFileConnector gfc;

    @RequestMapping(value = "/ispindel", method = RequestMethod.POST)
    public ResponseEntity<String> tester(@RequestBody ISpindelData isd) {
        Temperatures t = tfc.readTemperatures();
        t.appendTemperature(isd.getTemperature());
        boolean temperatureWritten = tfc.writeTemperatures(t);

        Batteries b = bfc.readBatteries();
        b.appendBattery(isd.getBattery());
        boolean batteryWritten = bfc.writeBatteries(b);

        Gravities g = gfc.readGravities();
        Double tilt = isd.getAngle();
        Double gravity = 0.8829738953763338 + 0.006232944160223315 * tilt - 0.00009307076837167256 * tilt * tilt + 6.366267580822437e-7 * tilt * tilt * tilt;
        g.appendGravity(gravity);
        boolean gravityWritten = gfc.writeGravities(g);

        if (temperatureWritten && batteryWritten && gravityWritten) {
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

}