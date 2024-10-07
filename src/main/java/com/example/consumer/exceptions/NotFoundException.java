package com.example.consumer.exceptions;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = -877442240955457458L;

    public NotFoundException(String message) {
        super(message);
    }
}
