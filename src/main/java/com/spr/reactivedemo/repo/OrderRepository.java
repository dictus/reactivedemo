package com.spr.reactivedemo.repo;

import com.spr.reactivedemo.module.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
