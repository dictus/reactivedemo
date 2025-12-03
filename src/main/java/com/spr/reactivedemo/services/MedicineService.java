package com.spr.reactivedemo.services;

import com.spr.reactivedemo.module.Medicine;
import com.spr.reactivedemo.repo.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    public Flux<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
}
