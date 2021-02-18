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
    FermentTemperaturesFileConnector ftfc;

    @Autowired //using config.ControllerDependencies
    FermentHeatCoolFileConnector fhcfc;

    @Autowired //using config.ControllerDependencies
    FermentBubblesFileConnector fbfc;

    @Autowired //using config.ControllerDependencies
    FermentConfigFileConnector fcfc;

    static FermentMeta fermentMeta = new FermentMeta();

    FermenterController(){
        fermentMeta.setLastGet(LocalDateTime.now());
    }

    @GetMapping("/ferment-temperatures")
    public FermentTemperatures retrieveTemperatures() {
        ObjectMapper mapper = new ObjectMapper();
        FermentTemperatures ft = ftfc.readFermentTemperatures();
        return ft;
    }

    @GetMapping("/ferment-heat-cools")
    public FermentHeatCools retrieveHeatCools() {
        ObjectMapper mapper = new ObjectMapper();
        FermentHeatCools fhc = fhcfc.readFermentHeatCools();
        return fhc;
    }

    @GetMapping("/ferment-bubbles")
    public FermentBubbles retrieveBubbles() {
        ObjectMapper mapper = new ObjectMapper();
        FermentBubbles fb = fbfc.readFermentBubbles();
        return fb;
    }

    @PostMapping(value = "/ferment-monitor")
    public ResponseEntity<String> receiveData(@RequestBody FermentMonitorData fmd) {
        FermentTemperatures ft = ftfc.readFermentTemperatures();
        Double shedTemp = fmd.getShedTemp();
        Double fridgeTemp = fmd.getFridgeTemp();
        Double wortTemp = fmd.getWortTemp();
        Double target = fmd.getTarget();
        Double tolerance = fmd.getTolerance();
        ft.appendTemperature(shedTemp,fridgeTemp,wortTemp, target, tolerance);
        boolean temperatureWritten = ftfc.writeFermentTemperatures(ft);

        FermentHeatCools fhc = fhcfc.readFermentHeatCools();
        Integer heating = fmd.getHeating();
        Integer cooling = fmd.getCooling();
        fhc.appendFermentHeatCool(cooling, heating);
        boolean heatCoolWritten = fhcfc.writeFermentHeatCools(fhc);

        FermentBubbles fb = fbfc.readFermentBubbles();
        Integer bubbles = fmd.getBubbles();
        fb.appendFermentBubbles(bubbles);
        boolean bubblesWritten = fbfc.writeFermentBubbles(fb);


        if (temperatureWritten && heatCoolWritten && bubblesWritten) {
            return new ResponseEntity<String>("Done it!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Oops!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ferment-config")
    public FermentConfig retrieveConfig() {
        fermentMeta.setLastGet(LocalDateTime.now());
        ObjectMapper mapper = new ObjectMapper();
        FermentConfig fc = fcfc.readConfig();
        return fc;
    }

    @GetMapping("/ferment-meta")
    public FermentMeta retrieveMeta() {

        return fermentMeta;
    }

}
