package com.spr.reactivedemo.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    private String date;
    private String startTime;
    private String endTime;
}
