package com.pragma.powerup.usermicroservice;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.domain.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserValidatorTest {
    private UserValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new UserValidator();
    }
    @Test
    void userNotNull() {
        // Given
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setName("Juan");
        requestDto.setLastName("Pérez");
        requestDto.setEmail("juan.perez@example.com");
        requestDto.setDni("123456789");
        requestDto.setCel("+573005698325");
        requestDto.setBirthday("01-01-2000");
        // When
        boolean result = validator.validate(requestDto);
        // Then
        Assertions.assertTrue(result);
    }
    @Test
     void testValidateEmail() {
        // Caso válido
        String correoValido = "correo@ejemplo.com";
        assertDoesNotThrow(() -> validator.validateEmail(correoValido));

        // Caso inválido
        String correoInvalido = "correoejemplo.com";
        assertThrows(RuntimeException.class, () -> validator.validateEmail(correoInvalido));
    }

    @Test
    void testValidateDni() {
        // Caso válido
        String dniValido = "123456789";
        assertDoesNotThrow(() -> validator.validateDni(dniValido));

        // Caso inválido
        String dniInvalido = "12a3456789";
        assertThrows(RuntimeException.class, () -> validator.validateDni(dniInvalido));
    }

    @Test
     void testValidateBirthday() {
        // Fecha válida
        String fechaValida = "01-01-2000";
        assertDoesNotThrow(() -> validator.validateBirthday(fechaValida));

        // Fecha inválida
        String fechaInvalida = "32-01-2000";
        assertThrows(RuntimeException.class, () -> validator.validateBirthday(fechaInvalida));
    }

    @Test
     void testValidatePhone() {
        // Caso válido
        String telefonoValido = "+573002256987";
        assertDoesNotThrow(() -> validator.validatePhone(telefonoValido));

        // Caso inválido
        String telefonoInvalido = "555-0104";
        assertThrows(RuntimeException.class, () -> validator.validatePhone(telefonoInvalido));
    }

    @Test
    void testValidateAge() {
        UserValidator validator = new UserValidator();

        // Fecha válida
        String fechaValida = "01-01-2000";
        assertDoesNotThrow(() -> validator.validateAge(fechaValida));

        // Fecha inválida
        String fechaInvalida = "01-01-2021";
        assertThrows(RuntimeException.class, () -> validator.validateAge(fechaInvalida));
    }

}







