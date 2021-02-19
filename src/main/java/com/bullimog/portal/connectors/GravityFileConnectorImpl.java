package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Batteries;
import com.bullimog.portal.models.Gravities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Paths;

public class GravityFileConnectorImpl implements GravityFileConnector{
    private String filename;

    public GravityFileConnectorImpl(String filename){
        this.filename=filename;
    }

    // Read from file to object
    public Gravities readGravities() {
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Gravities g = new Gravities(); //default to empty
        try {
            g = mapper.readValue(Paths.get(filename).toFile(), Gravities.class);
        }catch(IOException ex){
            writeGravities(g);
            System.out.println("iSpindel Gravity file missing, created an empty one " + ex);
        }
        return g;
    }

    //write object to json file
    public boolean writeGravities(Gravities g) {
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), g);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception at writeGravities: " + ex);
        }
        return rtn;
    }
}
