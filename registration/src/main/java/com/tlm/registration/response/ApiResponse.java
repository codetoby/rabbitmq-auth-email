package com.tlm.registration.response;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {

    private boolean success;
    private String message;
    private int status;

    public ApiResponse(boolean success, String message, HttpStatusCode status) {
        this.success = success;
        this.message = message;
        this.status = status.value();
    }

}