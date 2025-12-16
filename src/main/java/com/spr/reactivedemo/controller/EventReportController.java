package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.Event;
import com.spr.reactivedemo.module.LocationReport;
import com.spr.reactivedemo.repo.EventRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/scan/report")
@Slf4j
public class EventReportController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/eventDashboard")
    public Flux<LocationReport> getEventDashboard() {
        return eventRepository.findAll()
                .filter(e -> e.getName() != null && !e.getName().isEmpty())
                .filter(e -> e.getLocation() != null && !e.getLocation().isEmpty())
                .filter(e -> e.getCost() != null && e.getDuration() != null && e.getDuration() > 0)
                .groupBy(Event::getLocation)
                .flatMap(groupedFlux ->
                        groupedFlux.collectList().map(events -> {
                            String location = groupedFlux.key();
                            long total = events.size();
                            int totalCost = events.stream().mapToInt(Event::getCost).sum();
                            int totalDuration = events.stream().mapToInt(Event::getDuration).sum();
                            double ratio = Math.round(((double) totalCost / totalDuration) * 1000.0) / 1000.0;
                            return new LocationReport(location, total, ratio);
                        })
                );
    }

    @PostMapping(value = "/eventDashboard/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LocationReport> heavyEvents(@RequestBody @Valid Event eventsReq) {
        log.info("Req Events {}", eventsReq);


        return eventRepository.findAll()
                .filter(e -> e.getName() != null && !e.getName().isEmpty() && e.getName().equals(eventsReq.getName()))
                .filter(e -> e.getLocation() != null && !e.getLocation().isEmpty())
                .filter(e -> e.getCost() != null && e.getDuration() != null && e.getDuration() > 0)
                .groupBy(Event::getLocation)
                .flatMap(groupedFlux ->
                        groupedFlux.collectList().map(events -> {
                            String location = groupedFlux.key();
                            long total = events.size();
                            int totalCost = events.stream().mapToInt(Event::getCost).sum();
                            int totalDuration = events.stream().mapToInt(Event::getDuration).sum();
                            double ratio = Math.round(((double) totalCost / totalDuration) * 1000.0) / 1000.0;
                            return new LocationReport(location, total, ratio);
                        })
                );
    }

}
