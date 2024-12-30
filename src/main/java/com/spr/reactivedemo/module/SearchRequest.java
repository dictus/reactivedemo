package com.spr.reactivedemo.module;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SearchRequest(
        @NotBlank(message = "Keyword cannot be empty") String keyword,
        @Email(message = "Invalid email format") String email,
        Integer age
) {
    public boolean hasAnyValue() {
        return (keyword != null && !keyword.isEmpty()) ||
                (email != null && !email.isEmpty()) ||
                age != null;
    }
}
