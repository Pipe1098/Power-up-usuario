package com.pragma.powerup.usermicroservice.domain.exceptions;

public class UserNoAuthException extends RuntimeException{
    public UserNoAuthException(String message) {
        super(message);
    }
}
