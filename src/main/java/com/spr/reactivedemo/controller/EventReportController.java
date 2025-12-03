package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.Event;
import com.spr.reactivedemo.module.LocationReport;
import com.spr.reactivedemo.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scan/report")
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

}
