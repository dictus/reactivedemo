package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.SearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient  // Automatically configure WebTestClient
public class ReactiveSearchControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;  // Automatically inject WebTestClient

    @BeforeEach
    public void setUp() {
        // Initialization before each test (optional)
    }

    @Test
    public void testSearch_Success() {
        // Perform POST request and validate the response
        webTestClient.post().uri("/search")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue("{\"keyword\": \"test\", \"email\": \"test@example.com\", \"age\": 30}")
                .exchange()
                .expectStatus().isOk()  // Assert status 200 OK
                .expectBody(String.class)
                .isEqualTo("Search performed successfully with inputs: SearchRequest[keyword=test, email=test@example.com, age=30]");
    }

    @Test
    public void testSearch_Failure_NoParameters() {
        // Create a SearchRequest with no valid data
        String invalidRequest = "{\"keyword\": \"\", \"email\": \"\", \"age\": null}";

        // Perform POST request and validate the response
        webTestClient.post().uri("/search")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue(invalidRequest)
                .exchange()
                .expectStatus().isBadRequest()  // Assert status 400 BAD REQUEST
                .expectBody()
                .jsonPath("$.message").isEqualTo("Validation Errors: keyword: Keyword cannot be empty");
    }

    @Test
    public void testSearch_ValidationFailure() {
        // Create a SearchRequest with an invalid email
        String invalidEmailRequest = "{\"keyword\": \"test\", \"email\": \"invalid-email\", \"age\": 30}";

        // Perform POST request and validate the response
        webTestClient.post().uri("/search")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue(invalidEmailRequest)
                .exchange()
                .expectStatus().isBadRequest()  // Assert status 400 BAD REQUEST
                .expectBody()
                .jsonPath("$.message").isEqualTo("Validation Errors: email: Invalid email format");
    }
}
