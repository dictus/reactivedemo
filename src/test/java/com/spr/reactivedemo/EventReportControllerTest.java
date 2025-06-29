package com.spr.reactivedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
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
}