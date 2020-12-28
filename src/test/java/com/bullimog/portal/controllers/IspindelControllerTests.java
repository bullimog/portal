package com.bullimog.portal.controllers;

import com.bullimog.portal.connectors.TemperatureFileConnector;
import com.bullimog.portal.connectors.TemperatureFileConnectorImpl;
import com.bullimog.portal.models.Temperatures;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class IspindelControllerTests{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TemperatureFileConnector temperatureFileConnector;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getTemperaturesTest() throws Exception{
//        List<Student> students = new ArrayList<>();
//        Student student = new Student();
//        student.setId(1);
//        student.setName("Arun");
//        students.add(student);
        Temperatures temperatures = new Temperatures();
        temperatures.appendTemperature((float) 10.01);
        temperatures.appendTemperature((float) 10.02);
        Mockito.when(temperatureFileConnector.readTemperatures()).thenReturn(temperatures);
        mockMvc.perform(get("/brewery/temperatures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateTime", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.temperature[0]", Matchers.equalTo(10.01)));
    }
}
