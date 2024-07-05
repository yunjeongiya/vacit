package com.oursummer.vacit.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class APIResponse<T> {

    private String code;
    private String message;
    private T data;

    public static <T> APIResponse<T> ofSuccess(String message, T data) {
        return new APIResponse<>("200", message, data);
    }

    public static <T> APIResponse<T> ofCreated(String message, T data) {
        return new APIResponse<>("201", message, data);
    }
}