package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
    User getUserByDni(String dniNumber);
    Boolean validateOwner(String dni);
}
