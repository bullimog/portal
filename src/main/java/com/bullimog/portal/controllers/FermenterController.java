package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.FermentTemperaturesFileConnector;
import com.bullimog.portal.models.FermentTemperatures;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/brewery")

public class FermenterController {

    @Autowired //using config.ControllerDependencies
    FermentTemperaturesFileConnector ftfc;

    @GetMapping("/fermentTemperatures")
    public FermentTemperatures retrieveTemperatures() {
        ObjectMapper mapper = new ObjectMapper();
        FermentTemperatures ft = ftfc.readFermentTemperatures();
        return ft;
    }
}
