package com.spr.reactivedemo;

import com.spr.reactivedemo.module.Shift;
import com.spr.reactivedemo.services.ShiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureWebTestClient  // Automatically configure WebTestClient
/*
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration .class })
*/
public class ReactiveSearchControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;  // Automatically inject WebTestClient

    @Autowired
    private ShiftService shiftService;


    @BeforeEach
    public void setUp() {
        // Initialization before each test (optional)
    }

    @Test
    public void testSearch_Success() {
        // Perform POST request and validate the response
        webTestClient
                .mutateWith(SecurityMockServerConfigurers.csrf()) // Provide a valid CSRF token
                .post().uri("/search")
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

    @Test
    void testGetMergedShifts() {
        List<Shift> inputShifts = Arrays.asList(
                new Shift("2025-01-01", "09:00", "17:00"),
                new Shift("2025-01-01", "15:00", "22:00"),
                new Shift("2025-01-02", "09:00", "17:00"),
                new Shift("2025-01-03", "15:00", "22:00")
        );

        List<Shift> mergedShifts = Arrays.asList(
                new Shift("2025-01-01", "09:00", "22:00"),
                new Shift("2025-01-02", "09:00", "17:00"),
                new Shift("2025-01-03", "15:00", "22:00")
        ).stream().sorted(Comparator.comparing(Shift::getDate).reversed()).collect(Collectors.toList());


        // Perform the test
        webTestClient
                .mutateWith(SecurityMockServerConfigurers.csrf())
                .post()
                .uri("/api/colleague/shifts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(inputShifts), Shift.class)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Shift.class)
                .isEqualTo(mergedShifts);

    }

}
