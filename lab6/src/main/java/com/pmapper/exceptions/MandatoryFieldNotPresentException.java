package com.pmapper.exceptions;

public class MandatoryFieldNotPresentException extends Exception {

    public MandatoryFieldNotPresentException() {
    }

    public MandatoryFieldNotPresentException(String message) {
        super(message);
    }
}
