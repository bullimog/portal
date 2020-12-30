package com.bullimog.portal.config;

import com.bullimog.portal.connectors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Defines Beans, which will be found and autowired.
@Configuration

//@SpringBootApplication is in higher-level package, so this bean will be scanned automatically.
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

}
