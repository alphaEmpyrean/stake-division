package com.etherealhazel.stakediv.container;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class E2eWithServerContainerTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient client;
      
    @Test
    public void createContainer_None_ReturnContainerDto() {       
        
        ContainerDto containerDto = new ContainerDto("root");

        client.post()
            .uri("http://localhost:" + port + "/api/container")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(containerDto))
            .exchange()
            .expectStatus().isCreated()
            .expectBody(ContainerDto.class);
    }

    @Test
    @Sql({"sql/deleteAllRows.sql","sql/insert2Containers.sql"})
    public void getAllContainers_2Preloaded_ReturnList2ConttainerDtos() {       
        
        client.get()
            .uri("http://localhost:" + port + "/api/container")
            .exchange()
            .expectStatus().isAccepted()
            .expectBodyList(ContainerDto.class).hasSize(2);
    }
    
    @Test
    @Sql({"sql/deleteAllRows.sql","sql/insert2Containers.sql"})
    public void addChildContainer_2Preloaded_ReturnUpdatedParentContainerDto() {
        
        //From Sql script
        String parentUuid = "d576ab3b-8a36-4dff-b50c-9d9d4ca6072c", childUuid = "a81bc81b-dead-4e5d-abff-90865d1e13b1";
        
        client.patch()
            .uri("http://localhost:" + port + "/api/container/" + parentUuid + "/childContainer/" + childUuid)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ContainerDto.class);
    }
}
