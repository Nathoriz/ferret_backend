package com.android.ferret_backend.config.error.exceptions;

public class BadRequest extends RuntimeException{
    public BadRequest(String message) {
        super(message);
    }
}
