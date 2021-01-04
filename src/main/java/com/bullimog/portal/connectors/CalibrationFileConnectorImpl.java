package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Calibration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Paths;

public class CalibrationFileConnectorImpl implements CalibrationFileConnector {

    private String filename;

    public CalibrationFileConnectorImpl(String filename) { this.filename=filename;}

    @Override
    public Calibration readCalibration() {
        ObjectMapper mapper  = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Calibration c = new Calibration(0.0,0.0,0.0,0.0);
        try {
            c = mapper.readValue(Paths.get(filename).toFile(), Calibration.class);
        }catch(IOException ex){
            System.out.println("Exception when reading Calibration" + ex);
        }
        return c;
    }

    @Override
    public boolean writeCalibration(Calibration c) {
        boolean rtn = false;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(Paths.get(filename).toFile(), c);
            rtn=true;
        }catch(IOException ex){
            System.out.println("Exception while writing Calibration: " + ex);
        }
        return rtn;
    }
}
