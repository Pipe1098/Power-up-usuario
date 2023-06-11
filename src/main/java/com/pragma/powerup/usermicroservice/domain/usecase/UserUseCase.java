package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.MissingBirthdateValidationException;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IAuthServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.AgeNotValidException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IAuthServicePort authServicePort;
    private final IRolePersistencePort rolPersistencePort;

    public UserUseCase(IUserPersistencePort personPersistencePort, IAuthServicePort authServicePort, IRolePersistencePort rolPersistencePort) {
        this.userPersistencePort = personPersistencePort;
        this.authServicePort = authServicePort;
        this.rolPersistencePort = rolPersistencePort;
    }


    public void saveUser(User user, String rolAuthorized, Long idRole) {
        if (user.getBirthdate() != null && !user.getBirthdate().equals("")) {
            if (isAgeOwnerValidate(user.getBirthdate())) {
                String rolUser = authServicePort.getRole(Token.getToken());
                ValidateAuth validateAuth = new ValidateAuth();
                validateAuth.validateRol(rolUser, rolAuthorized);

                Role role = rolPersistencePort.getRole(idRole);
                user.setIdRole(role);
                this.userPersistencePort.saveUser(user);
            } else {
                throw new AgeNotValidException("The Owner must be 18 years older or more.");
            }
        } else {
            throw new MissingBirthdateValidationException("Birthdate is required and the format is: yyyy-MM-dd.");
        }

    }

    @Override
    public void saveOwner(User usuario) {
        saveUser(usuario, Constants.ROLE_ADMIN, Constants.OWNER_ROLE_ID);
    }

    @Override
    public void saveClient(User user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public void saveEmployee(User user) {
        saveUser(user, Constants.ROLE_OWNER, Constants.EMPLOYEE_ROLE_ID);
    }


    @Override
    public User getUserByDni(String dniNumber) {
        return userPersistencePort.getUserByDni(dniNumber);
    }

    @Override
    public Boolean validateOwner(String dni) {
        User usuario = getUserByDni(dni);
        return usuario.getIdRole().getId() == Constants.OWNER_ROLE_ID;
    }


    private boolean isAgeOwnerValidate(LocalDate birthdate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthdate, today).getYears();
        return age > Constants.AGE_OF_OWNER;
    }
}
