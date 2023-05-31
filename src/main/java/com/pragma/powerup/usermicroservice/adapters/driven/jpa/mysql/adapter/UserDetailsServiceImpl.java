package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.UserNotRegisterException;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UserNotRegisterException {
        UserEntity usuario = userRepository.findByMail(mail).orElseThrow(() -> new UsernameNotFoundException(Constants.PERSON_NOT_FOUND_MESSAGE));

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(usuario.getIdRole());

        return PrincipalUser.build(usuario, roles);
    }
}
