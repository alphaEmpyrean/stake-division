package com.etherealhazel.stakediv.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.etherealhazel.stakediv.dto.ContainerDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class integrationTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient client;
    
    @Test
    public void e2eTest() {       
        
        ContainerDto containerDto = new ContainerDto("root");

        client.post()
            .uri("http://localhost:" + port + "/api/container")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.ALL)
            .body(BodyInserters.fromValue(containerDto))
            .exchange()
            .expectStatus().isCreated();
    }
}
