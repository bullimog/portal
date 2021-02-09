package com.bullimog.portal.config;

import com.bullimog.portal.connectors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Defines Beans, which will be found and autowired.
@Configuration
public class ControllerDependencies {

    @Bean
    public BatteryFileConnector getBatteryFileConnector(@Value("${battery.filename}") String filename) {
        return new BatteryFileConnectorImpl(filename);
    }
    @Bean
    public TemperatureFileConnector getTemperatureFileConnector(@Value("${temperature.filename}") String filename) {
        return new TemperatureFileConnectorImpl(filename);
    }
    @Bean
    public GravityFileConnector getGravityFileConnector(@Value("${gravity.filename}") String filename) {
        return new GravityFileConnectorImpl(filename);
    }
    @Bean
    public CalibrationFileConnector getCalibrationFileConnector(@Value("${calibration.filename}") String filename) {
        return new CalibrationFileConnectorImpl(filename);
    }
    @Bean
    public FermentTemperaturesFileConnector getFermentTemperaturesFileConnector(@Value("${fermenttemperatures.filename}") String filename) {
        return new FermentTemperaturesFileConnectorImpl(filename);
    }

    @Bean
    public FermentHeatCoolFileConnector getFermentHeatCoolFileConnector(@Value("${fermentheatcool.filename}") String filename) {
        return new FermentHeatCoolFileConnectorImpl(filename);
    }

    @Bean
    public FermentBubblesFileConnector getFermentBubblesFileConnector(@Value("${fermentbubbles.filename}") String filename) {
        return new FermentBubblesFileConnectorImpl(filename);
    }
}
