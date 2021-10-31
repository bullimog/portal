package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.*;
import com.bullimog.portal.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/brewery")

public class FermenterController {

    @Autowired @Qualifier("ferment-temperature")
    FileConnector fermentTemperaturesFileConnector;

    @Autowired @Qualifier("ferment-heatcool")
    FileConnector fermentHeatCoolFileConnector;

    @Autowired @Qualifier("ferment-bubbles")
    FileConnector fermentBubblesFileConnector;

    @Autowired @Qualifier("ferment-config")
    FileConnector fermentConfigFileConnector;

    static FermentMeta fermentMeta = new FermentMeta();

    FermenterController(){
        fermentMeta.setLastGet(LocalDateTime.now());
        fermentMeta.setLastPost(LocalDateTime.now());
    }

    @GetMapping("/ferment-temperatures")
    public FermentTemperatures retrieveTemperatures() {
        ObjectMapper mapper = new ObjectMapper();
        return fermentTemperaturesFileConnector.readContents(FermentTemperatures.class).
                orElse(new FermentTemperatures());
    }

    @GetMapping("/ferment-heat-cools")
    public FermentHeatCools retrieveHeatCools() {
        ObjectMapper mapper = new ObjectMapper();
        return fermentHeatCoolFileConnector.readContents(FermentHeatCools.class).
                orElse(new FermentHeatCools());
    }

    @GetMapping("/ferment-bubbles")
    public FermentBubbles retrieveBubbles() {
        ObjectMapper mapper = new ObjectMapper();
        return fermentBubblesFileConnector.readContents(FermentBubbles.class).
                orElse(new FermentBubbles());
    }

    @PostMapping(value = "/ferment-monitor")
    public ResponseEntity<String> receiveData(@RequestBody @NotNull FermentMonitorData fmd) {
        FermentTemperatures ft = fermentTemperaturesFileConnector.readContents(FermentTemperatures.class).
                orElse(new FermentTemperatures());
        Double shedTemp = fmd.getShedTemp();
        Double fridgeTemp = fmd.getFridgeTemp();
        Double wortTemp = fmd.getWortTemp();
        Double target = fmd.getTarget();
        Double tolerance = fmd.getTolerance();
        ft.appendTemperature(shedTemp,fridgeTemp,wortTemp, target, tolerance);
        boolean temperatureWritten = fermentTemperaturesFileConnector.writeContents(ft);

        FermentHeatCools fhc = fermentHeatCoolFileConnector.readContents(FermentHeatCools.class).
                orElse(new FermentHeatCools());
        Integer heating = fmd.getHeating();
        Integer cooling = fmd.getCooling();
        fhc.appendFermentHeatCool(cooling, heating);
        boolean heatCoolWritten = fermentHeatCoolFileConnector.writeContents(fhc);

        FermentBubbles fb = fermentBubblesFileConnector.readContents(FermentBubbles.class).
                orElse(new FermentBubbles());
        Integer bubbles = fmd.getBubbles();
        fb.appendFermentBubbles(bubbles);
        boolean bubblesWritten = fermentBubblesFileConnector.writeContents(fb);


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
        return fermentConfigFileConnector.readContents(FermentConfig.class).
                orElse(new FermentConfig("none", 0.0, 100.00, 60000, 0.0, 0.0, 0.0 ));
    }

    @GetMapping("/ferment-meta")
    public FermentMeta retrieveMeta() {
        return fermentMeta;
    }

}
