package com.bullimog.portal.controllers;

import com.bullimog.portal.config.GravityConfig;
import com.bullimog.portal.connectors.BatteryFileConnector;
import com.bullimog.portal.connectors.GravityFileConnector;
import com.bullimog.portal.connectors.TemperatureFileConnector;
import com.bullimog.portal.models.Batteries;
import com.bullimog.portal.models.Gravities;
import com.bullimog.portal.models.ISpindelData;
import com.bullimog.portal.util.GravityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${gravity.calc.degree3.1}") Double degree31;
    @Value("${gravity.calc.degree3.2}") Double degree32;
    @Value("${gravity.calc.degree3.3}") Double degree33;
    @Value("${gravity.calc.degree3.4}") Double degree34;
    @Value("${gravity.calc.temp.num0}") Double num0;
    @Value("${gravity.calc.temp.num1}") Double num1;
    @Value("${gravity.calc.temp.num2}") Double num2;
    @Value("${gravity.calc.temp.num3}") Double num3;
    GravityConfig gravityConfig;

    @RequestMapping(value = "/ispindel", method = RequestMethod.POST)
    public ResponseEntity<String> tester(@RequestBody ISpindelData isd) {
        gravityConfig = new GravityConfig(degree31,degree32,degree33,degree34,num0,num1,num2,num3);
        GravityUtils gu = new GravityUtils(gravityConfig);
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
//        g.appendGravity(isd.getGravity(), isd.getGravity());
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