package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.TemperatureFileConnector;
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

    @RequestMapping(value = "/ispindel", method = RequestMethod.POST)
    public ResponseEntity<String> tester(@RequestBody ISpindelData isd) {
        Temperatures t = tfc.readTemperatures();
        t.appendTemperature(isd.getTemperature());
        if (tfc.writeTemperatures(t)) {
            return new ResponseEntity<String>("Done it!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Done it!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/temperatures")
    public Temperatures retrieveTemperatures() {
        ObjectMapper mapper = new ObjectMapper();
        Temperatures t = tfc.readTemperatures();
        return t;
    }
}