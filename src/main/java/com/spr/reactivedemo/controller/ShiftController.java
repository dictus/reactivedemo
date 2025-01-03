package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.Shift;
import com.spr.reactivedemo.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/colleague")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping("/shifts")
    public Mono<ResponseEntity<List<Shift>>> getMergedShifts(@RequestBody Flux<Shift> shifts) {
        return shifts
                .collectList() // Collect the Flux<Shift> into a List<Shift>
                .flatMap(shiftService::mergeShifts) // Call the service to process the list
                .map(mergedShifts -> ResponseEntity.ok(mergedShifts)); // Wrap the result in ResponseEntity
    }
}
