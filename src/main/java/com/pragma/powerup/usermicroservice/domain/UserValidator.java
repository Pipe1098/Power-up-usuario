package com.pragma.powerup.usermicroservice.domain;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class UserValidator {

    public void validateEmail(String correo) {
        String regularExpression = "[A-Za-z0-9+_.-]+@(.+\\.[A-Za-z]+)$";
        if (!match(regularExpression, correo)) {
            throw new RuntimeException(Constants.MALFORMED_EMAIL_EXCEPTION);
        }
    }

    public void validateDni(String documento) {
        String regularExpression = "^[0-9]+$";
        if (!match(regularExpression, documento)) {
            throw new RuntimeException(Constants.MALFORMED_DOCUMENT_EXCEPTION);
        }
    }

    public void validatePhone(String telefono) {
        String regularExpression = "^(\\+\\d{1,3})?((\\d{1,3})|\\d{1,3})\\d{3,4}\\d{4}$";
        if (!match(regularExpression, telefono)) {
            throw new RuntimeException(Constants.MALFORMED_PHONE_NUMBER_EXCEPTION);
        }
    }

    public LocalDate stringToDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(Constants.MALFORMED_BIRTHDATE_EXCEPTION);
        }
    }
    public void validateBirthday(String fechaNacimiento) {
        stringToDate(fechaNacimiento);
    }

    private boolean match(String regularExpression, String input) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public void validateAge(String fechaNacimiento) {
        LocalDate fechaNacimientoLocalDate = stringToDate(fechaNacimiento);
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaMayorEdad = fechaNacimientoLocalDate.plusYears(18);
        if (fechaActual.isBefore(fechaMayorEdad)) {
            throw new RuntimeException(Constants.OWNER_NOT_OF_LEGAL_AGE_EXCEPTION);
        }
    }

    public void validateClave(String clave) {
        // Agregar validación para la clave encriptada
    }
}


