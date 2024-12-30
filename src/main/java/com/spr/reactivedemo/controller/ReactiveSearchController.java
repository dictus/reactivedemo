package com.spr.reactivedemo.controller;

import com.spr.reactivedemo.module.SearchRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import jakarta.validation.Valid;

@RestController
public class   ReactiveSearchController {

    @PostMapping("/search")
    public Mono<String> search(@Valid @RequestBody Mono<SearchRequest> searchRequestMono) {
        return searchRequestMono.flatMap(searchRequest -> {
            if (isValidSearchRequest(searchRequest)) {
                return Mono.just("Search performed successfully with inputs: " + searchRequest);
            }
            return Mono.error(new IllegalArgumentException("At least one search parameter must be provided."));
        });
    }

    /*private boolean isValidSearchRequest(SearchRequest searchRequest) {
        // Check multiple conditions using Streams
        return Stream.of(
                () -> isValidKeyword(searchRequest.keyword()),
                () -> isValidEmail(searchRequest.email()),
                () -> Objects.nonNull(searchRequest.age())
        ).anyMatch(Supplier::get);
    }*/
    private boolean isValidSearchRequest(SearchRequest searchRequest) {
        if (isValidKeyword(searchRequest.keyword())) {
            return true;
        }
        if (isValidEmail(searchRequest.email())) {
            return true;
        }
        if (Objects.nonNull(searchRequest.age())) {
            return true;
        }
        return false;
    }



    private boolean isValidKeyword(String keyword) {
        return keyword != null && !keyword.isBlank();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches(".+@.+\\..+");
    }
}
