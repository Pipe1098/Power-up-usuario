package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void saveOwner(User user);
    void saveEmployee(User user);
    void saveClient(User toUser);
    User getUserByDni(String dniNumber);
    Boolean validateOwner(String dni);
}
