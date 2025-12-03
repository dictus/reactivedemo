package com.spr.reactivedemo.module;


import lombok.Data;

@Data
public class LocationReport {
    private String location;
    private long totalEvents;
    private double costDurationRatio;

    public LocationReport(String location, long totalEvents, double costDurationRatio) {
        this.location = location;
        this.totalEvents = totalEvents;
        this.costDurationRatio = costDurationRatio;
    }

    // Getters and setters (or use Lombok @Data)
}
