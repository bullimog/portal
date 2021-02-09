package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentBubbles;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Paths;

public class FermentBubblesFileConnectorImpl implements FermentBubblesFileConnector {

    private String filename;
    public FermentBubblesFileConnectorImpl(String filename){
        this.filename=filename;
    }

    @Override
    public FermentBubbles readFermentBubbles(){
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        FermentBubbles fb = new FermentBubbles(); //default to empty
        try {
            fb = mapper.readValue(Paths.get(filename).toFile(), FermentBubbles.class);
        }catch(IOException ex){
            System.out.println("Exception when reading Fermenter Bubbles file" + ex);
        }
        return fb;
    };

    @Override
    public boolean writeFermentBubbles(FermentBubbles fb){
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), fb);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception while writing Fermenter Bubbles: " + ex);
        }
        return rtn;
    };
}
