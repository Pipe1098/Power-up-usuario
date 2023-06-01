package com.pragma.powerup.usermicroservice.UserTest;

import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.MissingBirthdateValidationException;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IAuthServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.AgeNotValidException;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserNoAuthException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.Token;
import com.pragma.powerup.usermicroservice.domain.usecase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


    public class UserUseCaseTest {
        @Mock
        private IUserPersistencePort userPersistencePort;

        @Mock
        private IAuthServicePort authServicePort;

        @Mock
        private IRolePersistencePort rolPersistencePort;

        @InjectMocks
        private UserUseCase userUseCase;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void saveUserWithValidData() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", LocalDate.of(2000, 1, 1), "password", new Role(2L, "Owner", "The owner of a restaurant"));

            when(authServicePort.getRole(Token.getToken())).thenReturn(Constants.ROLE_ADMIN);
            when(rolPersistencePort.getRole(Constants.OWNER_ROLE_ID)).thenReturn(new Role(2L, "Owner", "The owner of a restaurant"));

            userUseCase.saveUser(user, Constants.ROLE_ADMIN, Constants.OWNER_ROLE_ID);

            verify(userPersistencePort).saveUser(user);
        }

        @Test
        public void saveUserWithNullBirthdate() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", null, "password", new Role(2L, "Owner", "The owner of a restaurant"));

            assertThrows(MissingBirthdateValidationException.class, () -> userUseCase.saveUser(user, Constants.ROLE_ADMIN, Constants.OWNER_ROLE_ID));
        }

        @Test
        public void saveUserWithInvalidAge() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", LocalDate.of(2010, 1, 1), "password", new Role(2L, "Owner", "The owner of a restaurant"));

            assertThrows(AgeNotValidException.class, () -> userUseCase.saveUser(user, Constants.ROLE_ADMIN, Constants.OWNER_ROLE_ID));
        }

        @Test
        public void saveOwner() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", LocalDate.of(2000, 1, 1), "password", new Role(2L, "ROLE_OWNER", "ROLE_OWNER"));

            when(rolPersistencePort.getRole(Constants.OWNER_ROLE_ID)).thenReturn(new Role(2L, "ROLE_OWNER", "ROLE_OWNER"));
            when(authServicePort.getRole(Token.getToken())).thenReturn(Constants.ROLE_ADMIN);
            userUseCase.saveOwner(user);

            verify(userPersistencePort).saveUser(user);
        }

        @Test
        public void saveClient() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", LocalDate.of(2000, 1, 1), "password", new Role(4L, "ROLE_CLIENT", "A client in the restaurant"));

            when(rolPersistencePort.getRole(Constants.CLIENT_ROLE_ID)).thenReturn(new Role(4L, "ROLE_CLIENT", "ROLE_CLIENT"));
                    userUseCase.saveClient(user);

            verify(userPersistencePort).saveUser(user);
        }

        @Test
        public void saveEmployeeWithValidData() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", LocalDate.of(2000, 1, 1), "password", new Role(4L, "Employee", "An employee in the restaurant"));

            when(authServicePort.getRole(Token.getToken())).thenReturn(Constants.ROLE_OWNER);
            when(rolPersistencePort.getRole(Constants.EMPLOYEE_ROLE_ID)).thenReturn(new Role(4L, "Employee", "An employee in the restaurant"));

            userUseCase.saveEmployee(user);

            verify(userPersistencePort).saveUser(user);
        }

        @Test
        public void saveEmployeeWithNullBirthdate() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", null, "password", new Role(4L, "Employee", "An employee in the restaurant"));

            assertThrows(MissingBirthdateValidationException.class, () -> userUseCase.saveEmployee(user));
        }

        @Test
        public void saveEmployeeWithInvalidRole() {
            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", LocalDate.of(2000, 1, 1), "password", new Role(2L, "ROLE_CLIENT", "CLIENT_ROLE"));
            when(authServicePort.getRole(Token.getToken())).thenReturn(Constants.ROLE_CLIENT);
            assertThrows(UserNoAuthException.class, () -> userUseCase.saveEmployee(user));
        }

        @Test
        public void getUserByDni() {
            String dni = "12345678A";

            userUseCase.getUserByDni(dni);

            verify(userPersistencePort).getUserByDni(dni);
        }

        @Test
        public void validateOwner() {
            String dni = "12345678A";

            User user = new User(1L, "12345678A", "John", "Doe", "johndoe@mail.com", "123456789", LocalDate.of(2000, 1, 1), "password", new Role(2L, "Owner", "The owner of a restaurant"));
            when(userPersistencePort.getUserByDni(dni)).thenReturn(user);

            boolean result = userUseCase.validateOwner(dni);

            assertTrue(result);
        }

}
