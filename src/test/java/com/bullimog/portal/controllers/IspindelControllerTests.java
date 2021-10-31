package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.*;
import com.bullimog.portal.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class IspindelControllerTests{

    @Autowired
    private MockMvc mockMvc;

    @MockBean @Qualifier("temperature")
    FileConnector temperatureFileConnector;

    @MockBean @Qualifier("battery")
    FileConnector batteryFileConnector;

    @MockBean @Qualifier("gravity")
    FileConnector gravityFileConnector;

    @MockBean @Qualifier("calibration")
    FileConnector calibrationFileConnector;

    @MockBean @Qualifier("ferment-temperature")
    FileConnector fermentTemperaturesFileConnector;

    @MockBean @Qualifier("ferment-heatcool")
    FileConnector fermentHeatCoolFileConnector;

    @MockBean @Qualifier("ferment-bubbles")
    FileConnector fermentBubblesFileConnector;

    @MockBean @Qualifier("ferment-config")
    FileConnector fermentConfigFileConnector;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getTemperaturesTest() throws Exception{
        Temperatures temperatures = new Temperatures();
        temperatures.appendTemperature(10.01);
        temperatures.appendTemperature(10.02);
        Mockito.when(temperatureFileConnector.readContents(Temperatures.class)).thenReturn(Optional.of(temperatures));
        mockMvc.perform(get("/brewery/temperatures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateTime", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.temperature[0]", Matchers.equalTo(10.01)));
    }

    @Test
    public void postIspindelDataTest() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        ISpindelData data = new ISpindelData("name", 12345, "token", 12.23,
                20.01, "C", 3.5, 1.160, 30, -72);
        String json = mapper.writeValueAsString(data);

        Temperatures temperatures = new Temperatures();
        temperatures.appendTemperature(10.01);
        temperatures.appendTemperature(10.02);
        Mockito.when(temperatureFileConnector.writeContents(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(temperatureFileConnector.readContents(Temperatures.class)).thenReturn(Optional.of(temperatures));

        Batteries batteries = new Batteries();
        batteries.appendBattery(3.5);
        batteries.appendBattery(3.6);
        Mockito.when(batteryFileConnector.writeContents(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(batteryFileConnector.readContents(Batteries.class)).thenReturn(Optional.of(batteries));

        Gravities gravities = new Gravities();
        gravities.appendGravity(1.040, 1.40);
        gravities.appendGravity(1.045, 1.040);
        Mockito.when(gravityFileConnector.writeContents(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(gravityFileConnector.readContents(Gravities.class)).thenReturn(Optional.of(gravities));

        Calibration calibration = new Calibration(0.0,0.0,0.0,0.0,0.0);
        Mockito.when(calibrationFileConnector.readContents(Calibration.class)).thenReturn(Optional.of(calibration));

        mockMvc.perform(post("/brewery/ispindel")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
