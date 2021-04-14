package com.caputo.finalWork.Services.Exceptions;

import javassist.SerialVersionUID;

public class NotFoundException extends RuntimeException{
    public static final long SerialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
