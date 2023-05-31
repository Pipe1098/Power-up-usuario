package com.pragma.powerup.usermicroservice.domain.api;

public interface IAuthServicePort {
    String getRole(String token);
}
