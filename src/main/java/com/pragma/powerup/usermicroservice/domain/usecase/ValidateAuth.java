package com.pragma.powerup.usermicroservice.domain.usecase;


import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserNoAuthException;

public class ValidateAuth {
    public ValidateAuth() {
    }
    public static void validateRol(String rolUsuario, String rolAutorizado){
        if(!rolUsuario.equals(rolAutorizado)){
            throw new UserNoAuthException(Constants.ROLE_NOT_ALLOWED_MESSAGE);
        }
    }
}
