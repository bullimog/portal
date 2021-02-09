package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentHeatCools;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Paths;

public class FermentHeatCoolFileConnectorImpl implements FermentHeatCoolFileConnector{

    private String filename;
    public FermentHeatCoolFileConnectorImpl(String filename){
        this.filename=filename;
    }

    @Override
    public FermentHeatCools readFermentHeatCools(){
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        FermentHeatCools fhc = new FermentHeatCools(); //default to empty
        try {
            fhc = mapper.readValue(Paths.get(filename).toFile(), FermentHeatCools.class);
        }catch(IOException ex){
            System.out.println("Exception when reading Fermenter Heat Cool file" + ex);
        }
        return fhc;
    };

    @Override
    public boolean writeFermentHeatCools(FermentHeatCools fhc){
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), fhc);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception while writing FermentHeatCools: " + ex);
        }
        return rtn;
    };
}
