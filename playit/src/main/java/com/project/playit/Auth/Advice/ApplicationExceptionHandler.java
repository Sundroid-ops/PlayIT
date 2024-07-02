package com.project.playit.Auth.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgs(MethodArgumentNotValidException e){
        Map<String, String> exmap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            exmap.put(err.getField(), err.getDefaultMessage());
        });

        return exmap;
    }
}
