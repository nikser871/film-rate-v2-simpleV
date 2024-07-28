package org.example.filmratev2simplev.controller;

import org.example.filmratev2simplev.exception.FilmException;
import org.example.filmratev2simplev.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({FilmException.class, UserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationException(RuntimeException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({NullPointerException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse objectNotFound(NullPointerException e){
        return new ErrorResponse("Object wasn't found");
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse objectNotFound(Throwable e){
        return new ErrorResponse("Ошибка!!!");
    }

    @ExceptionHandler({InternalServerError.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse internalError(Throwable e){
        return new ErrorResponse(e.getMessage());
    }



}
