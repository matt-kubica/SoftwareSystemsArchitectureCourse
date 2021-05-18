package com.pmapper.exceptions;

public class NoPublicSetterException extends Exception {

    public NoPublicSetterException() {
    }

    public NoPublicSetterException(String message) {
        super(message);
    }
}
