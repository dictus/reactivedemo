package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.Medicine;
import com.spr.reactivedemo.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Optional;

@RestController
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public Flux<Medicine> getMedicines() {
        return medicineService.getAllMedicines();
    }


    @GetMapping("/medicines2")
    public ResponseEntity<Flux<Medicine>> getMedicines2() {
        return ResponseEntity.of(Optional.of(medicineService.getAllMedicines()));
    }
}
