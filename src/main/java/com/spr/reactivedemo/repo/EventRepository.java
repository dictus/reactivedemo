package com.spr.reactivedemo.repo;

import com.spr.reactivedemo.module.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
}
