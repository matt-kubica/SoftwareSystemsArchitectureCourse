package com.pmapper.exceptions;

public class NoTextValueOnMandatoryFieldException extends Exception {

    public NoTextValueOnMandatoryFieldException() {
    }

    public NoTextValueOnMandatoryFieldException(String message) {
        super(message);
    }
}
