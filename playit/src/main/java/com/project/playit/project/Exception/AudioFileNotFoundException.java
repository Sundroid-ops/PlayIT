package com.project.playit.project.Exception;

public class AudioFileNotFoundException extends Exception{
    public AudioFileNotFoundException() {
    }

    public AudioFileNotFoundException(String message) {
        super(message);
    }

    public AudioFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AudioFileNotFoundException(Throwable cause) {
        super(cause);
    }

    public AudioFileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
