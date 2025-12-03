package com.spr.reactivedemo.repo;

import com.spr.reactivedemo.module.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
