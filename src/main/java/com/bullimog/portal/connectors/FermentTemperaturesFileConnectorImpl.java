package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentTemperatures;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileNotFoundException;
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
        }catch(FileNotFoundException ex){
            writeFermentTemperatures(ft);
            System.out.println("Ferment Temperatures file missing, created an empty one " + ex);
        }catch(IOException ex){
            System.out.println("Ferment Temperatures IOException " + ex);
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
