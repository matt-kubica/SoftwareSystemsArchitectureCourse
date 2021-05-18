package com.pmapper.exceptions;

public class NoDefaultConstructorException extends Exception {
    public NoDefaultConstructorException() {
    }

    public NoDefaultConstructorException(String message) {
        super(message);
    }
}
