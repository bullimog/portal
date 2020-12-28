package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.TemperatureFileConnector;
import com.bullimog.portal.models.ISpindelData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bullimog.portal.models.Temperatures;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;

@RestController
@RequestMapping(path="/brewery")
public class IspindelController {

    @RequestMapping(value = "/ispindel", method = RequestMethod.POST)
    public ResponseEntity<String> tester(@RequestBody ISpindelData isd) {
//        System.out.println("/ispindel ");
        TemperatureFileConnector tfc = new TemperatureFileConnector();
        Temperatures t = tfc.readTemperatures();
        t.appendTemperature(isd.getTemperature());
        tfc.writeTemperatures(t);
        return new ResponseEntity<String>("Done it!", HttpStatus.CREATED);
    }

    @GetMapping("/temperatures")
    public Temperatures retrieveTemperatures() {
//        System.out.println("/temperatures ");
        Temperatures t = new Temperatures();
//        try{
            ObjectMapper mapper = new ObjectMapper();

            //convert object to json String
            TemperatureFileConnector tfc = new TemperatureFileConnector();
            t = tfc.readTemperatures();
//            String json = mapper.writeValueAsString(t);
//            System.out.println("json: "+json);
//        }catch(com.fasterxml.jackson.core.JsonProcessingException ex){
//            System.out.println("ex:"+ex);
//        }
        return t;
    }
}

//    @PostMapping("/test2")
//    public Test createPerson(@RequestBody Test test) {
//        HttpHeaders headers = new HttpHeaders();
//        return new Test("whatever");
//    }
//    @PostMapping(value="/test", consumes="application/json")
//    public ResponseEntity postController(@RequestBody Test req) {
//        String s = req.getaValue();
//        System.out.println("gravity="+s);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

//    @PostMapping(value="/ispindel")
//    public ResponseEntity<ISpindelRequest> updateController(@RequestBody ISpindelRequest req)
//            throws URISyntaxException {
//        Long l = req.getGravity();
//        System.out.println("gravity="+l);
//        return ResponseEntity.ok(req);
//    }


//        ArrayList<Long> dateTimes = new ArrayList<Long>();
//        ArrayList<Long> temperatures = new ArrayList<Long>();
//        for(long n=0;n<10;n++){
//            dateTimes.add(new Long(n));
//            temperatures.add(new Long(n+10));
//        }
//
//        Temperatures t = new Temperatures(dateTimes, temperatures);

//    private Temperatures testTemperatures(){
//        ArrayList<Long> dateTimes = new ArrayList<Long>();
//        ArrayList<Long> temperatures = new ArrayList<Long>();
//        for(long n=0;n<10;n++){
//            dateTimes.add(new Long(n));
//            temperatures.add(new Long(n+10));
//        }
//
//        Temperatures t = new Temperatures(dateTimes, temperatures);
//        return t;
//    }


//        try{
//                ObjectMapper mapper = new ObjectMapper();
//
//                //convert object to json String
//                String json = mapper.writeValueAsString(t);
//                System.out.println("json: "+json);
//
//                //convert object to json file
//                mapper.writeValue(Paths.get("temperatures.json").toFile(), t);
//
//                //convert from json String to object
//                Temperatures t2 = mapper.readValue(json, Temperatures.class);
//        System.out.println(t2);
//
//        // convert from file to object
//        Temperatures t3 = mapper.readValue(Paths.get("temperatures.json").toFile(), Temperatures.class);
//        System.out.println(t3);
//        }catch(com.fasterxml.jackson.core.JsonProcessingException ex){
//        System.out.println("ex:"+ex);
//        }
//        catch(java.io.IOException ex){
//        System.out.println("ex2:"+ex);
//        }