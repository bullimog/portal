package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Temperatures;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Paths;
import java.io.IOException;

public class TemperatureFileConnectorImpl implements TemperatureFileConnector{

    private String filename;
    public TemperatureFileConnectorImpl(String filename){
        this.filename=filename;
    }

    // Read from file to object
    public Temperatures readTemperatures() {
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Temperatures t = new Temperatures(); //default to empty
        try {
            t = mapper.readValue(Paths.get(filename).toFile(), Temperatures.class);
        }catch(IOException ex){
            System.out.println("Exception at readTemperatures: " + ex);
            ex.printStackTrace();
        }
        return t;
    }

    //write object to json file
    public void writeTemperatures(Temperatures t) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), t);
        }catch(IOException ex){
            System.out.println("Exception at writeTemperatures: " + ex);
            ex.printStackTrace();
        }
    }
}
