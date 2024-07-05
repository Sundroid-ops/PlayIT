package com.project.playit.project.Advice;

import com.project.playit.Auth.Entity.ErrorMessage;
import com.project.playit.project.Exception.AudioFileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseApplicationConfiguration {

    @ExceptionHandler(AudioFileNotFoundException.class)
    public ResponseEntity<ErrorMessage> AudioFileNotFoundException(Exception e){
        ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
