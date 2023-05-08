package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "El documento de identidad es obligatorio")
    private String dni;

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String cel;

    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private String birthday;
}

