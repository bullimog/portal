package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.BatteryFileConnector;
import com.bullimog.portal.connectors.GravityFileConnector;
import com.bullimog.portal.connectors.TemperatureFileConnector;
import com.bullimog.portal.connectors.TemperatureFileConnectorImpl;
import com.bullimog.portal.models.Batteries;
import com.bullimog.portal.models.Gravities;
import com.bullimog.portal.models.ISpindelData;
import com.bullimog.portal.models.Temperatures;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class IspindelControllerTests{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TemperatureFileConnector temperatureFileConnector;

    @MockBean
    BatteryFileConnector batteryFileConnector;

    @MockBean
    GravityFileConnector gravityFileConnector;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getTemperaturesTest() throws Exception{
        Temperatures temperatures = new Temperatures();
        temperatures.appendTemperature(10.01);
        temperatures.appendTemperature(10.02);
        Mockito.when(temperatureFileConnector.readTemperatures()).thenReturn(temperatures);
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
        Mockito.when(temperatureFileConnector.writeTemperatures(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(temperatureFileConnector.readTemperatures()).thenReturn(temperatures);

        Batteries batteries = new Batteries();
        batteries.appendBattery(3.5);
        batteries.appendBattery(3.6);
        Mockito.when(batteryFileConnector.writeBatteries(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(batteryFileConnector.readBatteries()).thenReturn(batteries);

        Gravities gravities = new Gravities();
        gravities.appendGravity(1.040, 1.40);
        gravities.appendGravity(1.045, 1.040);
        Mockito.when(gravityFileConnector.writeGravities(ArgumentMatchers.any())).thenReturn(true);
        Mockito.when(gravityFileConnector.readGravities()).thenReturn(gravities);

        mockMvc.perform(post("/brewery/ispindel")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
