package com.spr.reactivedemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spr.reactivedemo.module.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Slf4j
public class EventReportControllerTest {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testGetEventDashboard() {
        webTestClient.get()
                .uri("/scan/report/eventDashboard")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].location").isNotEmpty()
                .jsonPath("$[0].totalEvents").isNumber()
                .jsonPath("$[0].costDurationRatio").isNumber();
    }

    @Test
    public void testGetEventDashboard2() throws JsonProcessingException {
        Mono<Event> eventMono = Mono.just(
                Event.builder().id(134L).name("test").build()
        );

        webTestClient.post()
                .uri("/scan/report/eventDashboard/v2")
                .contentType(MediaType.APPLICATION_JSON)
                .body(eventMono, Event.class)
                .exchange()
                .expectStatus().isOk()

                .expectBody()
                .consumeWith(result -> {
                    byte[] body = result.getResponseBody();
                    log.info("Response Body:\n{}", new String(body, StandardCharsets.UTF_8));
                })
                .jsonPath("$[0].location").isNotEmpty()
                .jsonPath("$[0].totalEvents").isNumber()
                .jsonPath("$[0].costDurationRatio").isNumber();

        //  .expectBody()
                /*.expectBodyList(LocationReport.class)
                .consumeWith(result -> {
                    List<LocationReport> response = result.getResponseBody();
                    log.info("Response Objects: {}", response); //also working
                });*/
        //  .expectBody()
              /*  .jsonPath("$[0].location").isNotEmpty()
                .jsonPath("$[0].totalEvents").isNumber()
                .jsonPath("$[0].costDurationRatio").isNumber()*/
        ;
    }
}