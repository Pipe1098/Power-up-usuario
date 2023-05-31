package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @Operation(summary = "Add a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/owner")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody UserRequestDto owner) {
        userHandler.saveOwner(owner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Employee already exists", content = @Content)
    })
    @PostMapping("/employee")
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody UserRequestDto employee) {
        userHandler.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "client created", content = @Content),
            @ApiResponse(responseCode = "409", description = "client already exists", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<Void> saveClient(@Valid @RequestBody UserRequestDto client) {
        userHandler.saveClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{dniNumber}")
    public ResponseEntity<UserResponseDto> getUserByDni(@PathVariable("dniNumber") String dniNumber){
        return ResponseEntity.ok(userHandler.getUserByDni(dniNumber));
    }

    @GetMapping("validate-owner/{dni}")
    public Boolean validateOwnerRol(@PathVariable("dni") String dni){
        return userHandler.validateOwner(dni);
    }
}
