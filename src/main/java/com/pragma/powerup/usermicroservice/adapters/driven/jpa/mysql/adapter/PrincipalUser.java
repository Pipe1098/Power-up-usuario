package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalUser implements UserDetails {
    private Long id;
    private Long idRestaurant;
    public Long getId() {
        return id;
    }
    public Long getIdRestaurant() {
        return idRestaurant;
    }
    public String getDni() {
        return dni;
    }

    private String nombre;
    private String dni;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(Long id, String nombre, String dni, String email, String password, Long idRestaurant, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.idRestaurant = idRestaurant;
        this.authorities = authorities;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
/*    public PrincipalUser(Long id, String nombre, String dni, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = dni;
        this.email = email;
        this.password = password;
        this.authorities = authorities;

    }*/

    public static PrincipalUser build(UserEntity usuario, List<RoleEntity> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
        return new PrincipalUser(usuario.getId(),usuario.getName(), usuario.getDniNumber(), usuario.getMail(),
                usuario.getPassword(),usuario.getIdRestaurant(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return dni;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
