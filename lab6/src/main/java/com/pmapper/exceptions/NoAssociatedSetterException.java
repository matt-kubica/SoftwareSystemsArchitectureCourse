package com.pmapper.exceptions;

import java.util.function.Supplier;

public class NoAssociatedSetterException extends Exception  {

    public NoAssociatedSetterException() {
    }

    public NoAssociatedSetterException(String message) {
        super(message);
    }
}
