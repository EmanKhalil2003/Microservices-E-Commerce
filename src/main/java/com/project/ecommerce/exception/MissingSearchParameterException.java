package com.project.ecommerce.exception;


public class MissingSearchParameterException extends RuntimeException {
    public MissingSearchParameterException(String message) {
        super(message);
    }
}

