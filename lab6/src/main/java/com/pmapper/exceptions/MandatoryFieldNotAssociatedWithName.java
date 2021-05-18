package com.pmapper.exceptions;

public class MandatoryFieldNotAssociatedWithName extends Exception {

    public MandatoryFieldNotAssociatedWithName() {
    }

    public MandatoryFieldNotAssociatedWithName(String message) {
        super(message);
    }
}
