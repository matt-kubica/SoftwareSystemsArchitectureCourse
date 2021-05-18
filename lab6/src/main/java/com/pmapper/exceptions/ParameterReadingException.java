package com.pmapper.exceptions;

public class ParameterReadingException extends Exception {

    public ParameterReadingException() {
    }

    public ParameterReadingException(String message) {
        super(message);
    }

    public ParameterReadingException(Throwable cause) {
        super(cause);
    }
}
