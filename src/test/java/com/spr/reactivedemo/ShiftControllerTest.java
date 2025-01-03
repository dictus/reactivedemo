package com.spr.reactivedemo;

import com.spr.reactivedemo.controller.ShiftController;
import com.spr.reactivedemo.module.Shift;
import com.spr.reactivedemo.services.ShiftService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
@WebFluxTest(controllers = ShiftController.class)
@Import(ShiftService.class) // Load the real ShiftService
// Load only the controller for testing
public class ShiftControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ShiftService shiftService;

    @Test
    void testGetMergedShifts() {
        // Mock input and output
        List<Shift> inputShifts = Arrays.asList(
                new Shift("2025-01-01", "09:00", "17:00"),
                new Shift("2025-01-01", "16:00", "22:00")
        );

        List<Shift> mergedShifts = Arrays.asList(
                new Shift("2025-01-01", "09:00", "22:00")
        );

        // Mock the service
        //when(shiftService.mergeShifts(inputShifts)).thenReturn(Mono.just(mergedShifts));

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

        // Verify service interactions
        verify(shiftService, times(1)).mergeShifts(inputShifts);
    }
}
