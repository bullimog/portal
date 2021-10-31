package com.bullimog.portal.config;

import com.bullimog.portal.connectors.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Defines Beans, which will be found and autowired.
//Configuration values defined in resources/application.properties
@Configuration
public class ControllerDependencies {
    @Bean @Qualifier("battery")
    public FileConnector getBatteryFileConnector(@Value("${battery.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }

    @Bean @Qualifier("temperature")
    public FileConnector getTemperatureFileConnector(@Value("${temperature.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }

    @Bean @Qualifier("gravity")
    public FileConnector getGravityFileConnector(@Value("${gravity.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }

    @Bean @Qualifier("calibration")
    public FileConnector getCalibrationFileConnector(@Value("${calibration.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }

    @Bean @Qualifier("ferment-temperature")
    public FileConnector getFermentTemperaturesFileConnector(@Value("${fermenttemperatures.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }

    @Bean @Qualifier("ferment-heatcool")
    public FileConnector getFermentHeatCoolFileConnector(@Value("${fermentheatcool.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }

    @Bean @Qualifier("ferment-bubbles")
    public FileConnector getFermentBubblesFileConnector(@Value("${fermentbubbles.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }

    @Bean @Qualifier("ferment-config")
    public FileConnector getFermentConfigFileConnector(@Value("${fermentconfig.filename}") String filename) {
        return new FileConnectorImpl(filename);
    }
}
