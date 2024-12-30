package com.spr.reactivedemo.controller;

import java.util.Objects;

public class ValidationUtils {
    public static boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }

    public static boolean isEmail(String value) {
        return value != null && value.matches(".+@.+\\..+");
    }
}
