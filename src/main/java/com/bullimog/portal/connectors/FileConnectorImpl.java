package com.bullimog.portal.connectors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class FileConnectorImpl implements FileConnector{

    private final String filename;
    public FileConnectorImpl(String filename){ this.filename=filename; }

    public <T> T readContents(Class<T> valueType){
        T t = null;

        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            t = mapper.readValue(Paths.get(filename).toFile(), valueType);

        }catch(FileNotFoundException ex){
            writeContents(valueType);
            System.out.println(""+valueType.getName()+" file missing, created an empty one" + ex);
        }
        catch(IOException ex){
            System.out.println("Exception while reading file: " + valueType.getName() + ex);
        }
        return t;
    }

    public <T> boolean writeContents(T t){
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), t);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception at write: " + t.getClass() + ex);
        }
        return rtn;
    };
}
