package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort personServicePort;
    private final IUserRequestMapper personRequestMapper;
    private final IUserResponseMapper personResponseMapper;

    @Override
    public void saveOwner(UserRequestDto userRequestDto) {
        personServicePort.saveOwner(personRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void saveEmployee(UserRequestDto userRequestDto) {
        personServicePort.saveEmployee(personRequestMapper.toUser(userRequestDto));
    }

 /*   @Override
    public void saveClient(UserRequestDto userRequestDto) {
        personServicePort.saveClient(personRequestMapper.toUser(userRequestDto));

    }*/

    @Override
    public UserResponseDto getUserByDni(String dniNumber) {
        return personResponseMapper.userToPersonResponse(personServicePort.getUserByDni(dniNumber));
    }

    @Override
    public Boolean validateOwner(String dni) {
        return personServicePort.validateOwner(dni);
    }

}
