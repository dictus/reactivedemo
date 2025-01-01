package com.spr.reactivedemo.services;

import com.spr.reactivedemo.module.Shift;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class ShiftService {

    public List<Shift> mergeShifts(List<Shift> shifts) {
        if (shifts == null || shifts.isEmpty()) {
            return new ArrayList<>();
        }

        // Sort shifts by start time
        shifts.sort(Comparator.comparingInt(Shift::getStart));

        // Initialize result list with the first shift
        List<Shift> mergedShifts = new ArrayList<>();
        mergedShifts.add(shifts.get(0));

        for (int i = 1; i < shifts.size(); i++) {
            // Get the last merged shift
            Shift lastMergedShift = mergedShifts.get(mergedShifts.size() - 1);
            Shift currentShift = shifts.get(i);

            // Merge if overlapping or contiguous
            if (currentShift.getStart() <= lastMergedShift.getEnd()) {
                lastMergedShift.setEnd(Math.max(lastMergedShift.getEnd(), currentShift.getEnd()));
            } else {
                // Otherwise, add the current shift to the result list
                mergedShifts.add(currentShift);
            }
        }
        log.info("mergd");
        return mergedShifts;
    }
}
