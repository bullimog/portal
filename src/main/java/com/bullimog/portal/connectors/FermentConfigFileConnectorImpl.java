package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentConfig;
import com.bullimog.portal.models.FermentHeatCools;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Paths;

public class FermentConfigFileConnectorImpl implements FermentConfigFileConnector{
    private String filename;
    public FermentConfigFileConnectorImpl(String filename){
        this.filename=filename;
    }

    @Override
    public FermentConfig readConfig(){
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        FermentConfig fc = new FermentConfig("none", 0.0, 100.00, 60000, 0.0, 0.0, 0.0 );
        try {
            fc = mapper.readValue(Paths.get(filename).toFile(), FermentConfig.class);
        }catch(IOException ex){
            System.out.println("Exception when reading Fermenter Config file" + ex);
        }
        return fc;
    }

    @Override
    public boolean writeFermentConfig(FermentConfig fc){
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), fc);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception while writing Ferment Config: " + ex);
        }
        return rtn;
    }
}
