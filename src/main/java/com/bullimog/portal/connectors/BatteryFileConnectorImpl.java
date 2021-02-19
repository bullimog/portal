package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Batteries;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.nio.file.Paths;
import java.io.IOException;

public class BatteryFileConnectorImpl implements BatteryFileConnector {
    private String filename;

    public BatteryFileConnectorImpl(String filename){
        this.filename=filename;
    }

    // Read from file to object
    public Batteries readBatteries() {
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Batteries b = new Batteries(); //default to empty
        try {
            b = mapper.readValue(Paths.get(filename).toFile(), Batteries.class);
        }catch(IOException ex){
            writeBatteries(b);
            System.out.println("Batteries file missing, created an empty one" + ex);
        }
        return b;
    }

    //write object to json file
    public boolean writeBatteries(Batteries b) {
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), b);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception at writeBatteries: " + ex);
        }
        return rtn;
    }
}
