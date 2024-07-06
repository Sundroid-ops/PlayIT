package com.project.playit.project.Exception;

public class PlayListNotFoundException extends RuntimeException{
    public PlayListNotFoundException() {
    }

    public PlayListNotFoundException(String message) {
        super(message);
    }

    public PlayListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayListNotFoundException(Throwable cause) {
        super(cause);
    }

    public PlayListNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
