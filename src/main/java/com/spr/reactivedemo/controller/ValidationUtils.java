package com.spr.reactivedemo.controller;

public class ValidationUtils {
    public static boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }

    public static boolean isEmail(String value) {
        return value != null && value.matches(".+@.+\\..+");
    }
}
