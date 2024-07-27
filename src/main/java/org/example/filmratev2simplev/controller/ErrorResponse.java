package org.example.filmratev2simplev.controller;


import lombok.Data;

@Data
public class ErrorResponse {

    String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

}
