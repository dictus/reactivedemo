package com.spr.reactivedemo.services;

import com.spr.reactivedemo.module.Shift;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShiftService {



    public Mono<List<Shift>> mergeShifts(List<Shift> shifts) {
        if (shifts == null || shifts.isEmpty()) {
            return Mono.just(new ArrayList<>());
        }

        // Group shifts by date
        Map<String, List<Shift>> shiftsByDate = shifts.stream()
                .collect(Collectors.groupingBy(Shift::getDate));

        List<Shift> mergedShifts = new ArrayList<>();

        // Process each group separately
        for (Map.Entry<String, List<Shift>> entry : shiftsByDate.entrySet()) {
            String date = entry.getKey();
            List<Shift> dailyShifts = entry.getValue();

            // Sort daily shifts by startTime
            dailyShifts.sort(Comparator.comparing(shift -> LocalTime.parse(shift.getStartTime())));

            // Initialize result list for the current date
            List<Shift> mergedDailyShifts = new ArrayList<>();
            mergedDailyShifts.add(dailyShifts.get(0));

            for (int i = 1; i < dailyShifts.size(); i++) {
                // Get the last merged shift
                Shift lastMergedShift = mergedDailyShifts.get(mergedDailyShifts.size() - 1);
                Shift currentShift = dailyShifts.get(i);

                LocalTime lastEndTime = LocalTime.parse(lastMergedShift.getEndTime());
                LocalTime currentStartTime = LocalTime.parse(currentShift.getStartTime());
                LocalTime currentEndTime = LocalTime.parse(currentShift.getEndTime());

                // Merge if overlapping or contiguous
                if (!currentStartTime.isAfter(lastEndTime)) {
                    lastMergedShift.setEndTime(lastEndTime.isAfter(currentEndTime)
                            ? lastEndTime.toString()
                            : currentEndTime.toString());
                } else {
                    // Otherwise, add the current shift to the result list
                    mergedDailyShifts.add(currentShift);
                }
            }

            // Add the merged shifts for this date to the final result
            mergedShifts.addAll(mergedDailyShifts);
        }

        return Mono.just(mergedShifts);
    }


}
