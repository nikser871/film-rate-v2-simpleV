package org.example.filmratev2simplev.controller;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server error!!!")
public class InternalServerError extends RuntimeException {
}
