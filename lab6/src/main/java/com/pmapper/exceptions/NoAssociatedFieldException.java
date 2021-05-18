package com.pmapper.exceptions;

public class NoAssociatedFieldException extends Exception {
    public NoAssociatedFieldException() {
    }

    public NoAssociatedFieldException(String message) {
        super(message);
    }
}
