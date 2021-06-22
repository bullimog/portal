package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.FermentBubblesFileConnector;
import com.bullimog.portal.connectors.FermentConfigFileConnector;
import com.bullimog.portal.connectors.FermentHeatCoolFileConnector;
import com.bullimog.portal.connectors.FermentTemperaturesFileConnector;
import com.bullimog.portal.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/brewery")

public class FermenterController {

    @Autowired //using config.ControllerDependencies
    FermentTemperaturesFileConnector fermentTemperaturesFileConnector;

    @Autowired //using config.ControllerDependencies
    FermentHeatCoolFileConnector fermentHeatCoolFileConnector;

    @Autowired //using config.ControllerDependencies
    FermentBubblesFileConnector fermentBubblesFileConnector;

    @Autowired //using config.ControllerDependencies
    FermentConfigFileConnector fermentConfigFileConnector;

    static FermentMeta fermentMeta = new FermentMeta();

    FermenterController(){
        fermentMeta.setLastGet(LocalDateTime.now());
        fermentMeta.setLastPost(LocalDateTime.now());
    }

    @GetMapping("/ferment-temperatures")
    public FermentTemperatures retrieveTemperatures() {
        ObjectMapper mapper = new ObjectMapper();
        return fermentTemperaturesFileConnector.readFermentTemperatures();
    }

    @GetMapping("/ferment-heat-cools")
    public FermentHeatCools retrieveHeatCools() {
        ObjectMapper mapper = new ObjectMapper();
        return fermentHeatCoolFileConnector.readFermentHeatCools();
    }

    @GetMapping("/ferment-bubbles")
    public FermentBubbles retrieveBubbles() {
        ObjectMapper mapper = new ObjectMapper();
        return fermentBubblesFileConnector.readFermentBubbles();
    }

    @PostMapping(value = "/ferment-monitor")
    public ResponseEntity<String> receiveData(@RequestBody FermentMonitorData fmd) {
        FermentTemperatures ft = fermentTemperaturesFileConnector.readFermentTemperatures();
        Double shedTemp = fmd.getShedTemp();
        Double fridgeTemp = fmd.getFridgeTemp();
        Double wortTemp = fmd.getWortTemp();
        Double target = fmd.getTarget();
        Double tolerance = fmd.getTolerance();
        ft.appendTemperature(shedTemp,fridgeTemp,wortTemp, target, tolerance);
        boolean temperatureWritten = fermentTemperaturesFileConnector.writeFermentTemperatures(ft);

        FermentHeatCools fhc = fermentHeatCoolFileConnector.readFermentHeatCools();
        Integer heating = fmd.getHeating();
        Integer cooling = fmd.getCooling();
        fhc.appendFermentHeatCool(cooling, heating);
        boolean heatCoolWritten = fermentHeatCoolFileConnector.writeFermentHeatCools(fhc);

        FermentBubbles fb = fermentBubblesFileConnector.readFermentBubbles();
        Integer bubbles = fmd.getBubbles();
        fb.appendFermentBubbles(bubbles);
        boolean bubblesWritten = fermentBubblesFileConnector.writeFermentBubbles(fb);


        if (temperatureWritten && heatCoolWritten && bubblesWritten) {
            fermentMeta.setLastPost(LocalDateTime.now());
            return new ResponseEntity<String>("Done it!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Oops!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ferment-config")
    public FermentConfig retrieveConfig() {
        fermentMeta.setLastGet(LocalDateTime.now());
        ObjectMapper mapper = new ObjectMapper();
        return fermentConfigFileConnector.readConfig();
    }

    @GetMapping("/ferment-meta")
    public FermentMeta retrieveMeta() {
        return fermentMeta;
    }

}
