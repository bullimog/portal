package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentTemperatures;
import com.bullimog.portal.models.Temperatures;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Paths;

public class FermentTemperaturesFileConnectorImpl implements FermentTemperaturesFileConnector{

    private String filename;
    public FermentTemperaturesFileConnectorImpl(String filename){
        this.filename=filename;
    }

    @Override
    public FermentTemperatures readFermentTemperatures(){
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        FermentTemperatures ft = new FermentTemperatures(); //default to empty
        try {
            ft = mapper.readValue(Paths.get(filename).toFile(), FermentTemperatures.class);
        }catch(IOException ex){
            System.out.println("Exception when reading Temperatures" + ex);
        }
        return ft;
    }

    @Override
    public boolean writeFermentTemperatures(FermentTemperatures ft){
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), ft);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception while writing FermentTemperatures: " + ex);
        }
        return rtn;
    }
}
