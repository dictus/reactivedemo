package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.Shift;
import com.spr.reactivedemo.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colleague")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping("/shifts")
    public ResponseEntity<List<Shift>> getMergedShifts(@RequestBody List<Shift> shifts) {
        List<Shift> mergedShifts = shiftService.mergeShifts(shifts);
        return ResponseEntity.ok(mergedShifts);
    }
}
