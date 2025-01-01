package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.Medicine;
import com.spr.reactivedemo.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public Flux<Medicine> getMedicines() {
        return medicineService.getAllMedicines();
    }
}
