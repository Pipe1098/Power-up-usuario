package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

public interface IUserHandler {

    void saveOwner(UserRequestDto userRequestDto);
    void saveEmployee(UserRequestDto userRequestDto);
    void saveClient(UserRequestDto client);
    UserResponseDto getUserByDni(String dniNumber);
    Boolean validateOwner(String dni);

}
