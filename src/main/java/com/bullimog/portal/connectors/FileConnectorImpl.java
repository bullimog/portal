package com.bullimog.portal.connectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

public class FileConnectorImpl implements FileConnector{

    private final String filename;
    public FileConnectorImpl(String filename){
        this.filename=filename;
    }

    public <T> Optional<T> readContents(Class<T> valueType){
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return Optional.of(mapper.readValue(Paths.get(filename).toFile(), valueType));

        }catch(FileNotFoundException ex){
            writeContents(valueType);
            System.out.println("The "+valueType.getName()+" file is missing, created an empty one" + ex);
        }
        catch(IOException ex){
            System.out.println("Exception while reading file: " + valueType.getName() + ex);
        }
        return Optional.empty();
    }

    public <T> boolean writeContents(T t){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), t);
            return true;
        }catch(IOException ex){
            System.out.println("Exception at write: " + t.getClass() + ex);
        }
        return false;
    };
}
