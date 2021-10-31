package com.bullimog.portal.controllers;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.http.HttpResponse;
import wiremock.org.apache.http.client.methods.HttpGet;
import wiremock.org.apache.http.impl.client.CloseableHttpClient;
import wiremock.org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

//Doesn't get executed
@DisplayName("=====================> An IT Test Class")
class IspindelControllerIntTests {
    private RestTemplate restTemplate;
    private WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
    }

    @Test
    @DisplayName("First test")
    void firstTest() {
        System.out.println("First test method");
    }

    @Test
    @DisplayName("Second test")
    void secondTest() {
        System.out.println("Second test method");
    }

    @Test
    @DisplayName("=====================> Should do what it aught to")
    void receiveData() throws IOException {
        System.out.println("######################################");
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        givenThat(get(urlEqualTo("/brewery/ferment-data"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello world!")
                        .withStatus(200)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8080/brewery/ferment-data");
        HttpResponse response = httpClient.execute(request);
//        String serverUrl = buildApiMethodUrl();
//        ResponseEntity<String> response = restTemplate.getForEntity(serverUrl, String.class);
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
//        assertThat(testClient.get("/some/thing").statusCode(), is(200));
//        assertThat(testClient.get("/some/thing/else").statusCode(), is(404));
        wireMockServer.stop();
    }

//    private String buildApiMethodUrl() {
//        return String.format("http://localhost:%d/some/thing",
//                this.wireMockServer.port()
//        );
//    }
}
