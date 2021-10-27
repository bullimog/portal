package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.*;
import com.bullimog.portal.models.*;
import com.bullimog.portal.util.GravityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/brewery")
@Api(value = "brewery1",produces="produces stuff")
public class IspindelController {

    @Autowired //using config.ControllerDependencies
    TemperatureFileConnector temperatureFileConnector;

    @Autowired //using config.ControllerDependencies
    BatteryFileConnector batteryFileConnector;

    @Autowired //using config.ControllerDependencies
    GravityFileConnector gravityFileConnector;

    @Autowired //using config.ControllerDependencies
    CalibrationFileConnector calibrationFileConnector;

    static ISpindelMeta iSpindelMeta = new ISpindelMeta();

    IspindelController(){
        iSpindelMeta.setLastPost(LocalDateTime.now());
    }

    @RequestMapping(value = "/ispindel", method = RequestMethod.POST)
    @ApiOperation(value = "POSTs date from iSpindel", notes = "Return 200", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully received iSpindel data", response = String.class),
            @ApiResponse(code = 500, message = "Internal server error - failed to write iSpindel data", response = String.class)}
    )
    public ResponseEntity<String> tester(@RequestBody ISpindelData isd) {
        GravityUtils gu = new GravityUtils(calibrationFileConnector.readCalibration());
        Temperatures t = temperatureFileConnector.readTemperatures();
        Double temperature = isd.getTemperature();
        t.appendTemperature(temperature);
        boolean temperatureWritten = temperatureFileConnector.writeTemperatures(t);

        Batteries b = batteryFileConnector.readContents(Batteries.class);
        if(b==null){ b = new Batteries(); }
        b.appendBattery(isd.getBattery());
        boolean batteryWritten = batteryFileConnector.writeContents(b);

        Gravities g = gravityFileConnector.readContents(Gravities.class);
        Double tilt = isd.getAngle();
        Double calculatedGravity = gu.calculateGravity(tilt);
        Double adjustedGravity = gu.adjustGravityForTemperatureC(calculatedGravity, temperature);
        g.appendGravity(isd.getGravity(), adjustedGravity);
        boolean gravityWritten = gravityFileConnector.writeContents(g);

        if (temperatureWritten && batteryWritten && gravityWritten) {
            iSpindelMeta.setLastPost(LocalDateTime.now());
            iSpindelMeta.setAngle(isd.getAngle());
            return new ResponseEntity<String>("Done it!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Oops!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/temperatures")
    @ApiOperation(value = "GETs list of iSpindel teperatures", notes = "Return 200", response = Temperatures.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved iSpindel data", response = Temperatures.class),
            @ApiResponse(code = 500, message = "Internal server error - failed to read iSpindel data")}
    )
    public Temperatures retrieveTemperatures() {
        ObjectMapper mapper = new ObjectMapper();
        return temperatureFileConnector.readTemperatures();
    }

    @GetMapping("/batteries")
    public Batteries retrieveBatteries() {
        ObjectMapper mapper = new ObjectMapper();
        //Batteries b = new Batteries();
        return batteryFileConnector.readContents(Batteries.class);
    }

    @GetMapping("/gravities")
    public Gravities retrieveGravities() {
        ObjectMapper mapper = new ObjectMapper();
        return gravityFileConnector.readContents(Gravities.class);
    }

    @GetMapping("/ispindel-meta")
    public ISpindelMeta retrieveMeta() {
        return iSpindelMeta;
    }
}