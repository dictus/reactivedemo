package com.spr.reactivedemo.repo;

import com.spr.reactivedemo.module.Medicine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MedicineRepository extends ReactiveCrudRepository<Medicine, Long> {
}
