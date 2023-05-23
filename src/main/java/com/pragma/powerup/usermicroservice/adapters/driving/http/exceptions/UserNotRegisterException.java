package com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions;

public class UserNotRegisterException extends RuntimeException{
    public UserNotRegisterException(String message) {
        super(message);
    }
}
