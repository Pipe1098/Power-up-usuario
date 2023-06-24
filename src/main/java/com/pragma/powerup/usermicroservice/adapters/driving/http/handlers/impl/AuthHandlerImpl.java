package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IAuthServicePort;
import com.pragma.powerup.usermicroservice.domain.usecase.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class AuthHandlerImpl implements IAuthHandler, IAuthServicePort {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getMail(), loginRequestDto.getPassword())
        );
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        Token.setToken(jwt);
       // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new JwtResponseDto(jwt);
    }

    @Override
    public JwtResponseDto refresh(JwtResponseDto jwtResponseDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtResponseDto);
        return new JwtResponseDto(token);
    }

    @Override
    public String getIdUser(String token) {
         return jwtProvider.getIdUserFromToken(token);
    }

    @Override
    public String getRoleUser(String token) {
        return jwtProvider.getRolesFromToken(token);
    }
    @Override
    public String getMailUser(String token) {
        return jwtProvider.getEmailFromToken(token);
    }

    @Override
    public String getIdRestaurantUser(String token) {
        return jwtProvider.getIdRestaurantFromToken(token);
    }
    @Override
    public String getDniUsuarioFromToken(String token) {
        return jwtProvider.getDniUsuarioFromToken(token);
    }

    @Override
    public String getRole(String token) {
        return jwtProvider.getRolesFromToken(token);
    }
}
