package com.pragma.powerup.usermicroservice.configuration.security.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.PrincipalUser;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        PrincipalUser usuarioPrincipal = (PrincipalUser) authentication.getPrincipal();
        //List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String dni=usuarioPrincipal.getDni();
        String mail=usuarioPrincipal.getEmail();
        Long id =usuarioPrincipal.getId();
        Long idRestaurant =usuarioPrincipal.getIdRestaurant();
        System.out.println(idRestaurant);
        String roles= usuarioPrincipal.getAuthorities().iterator().next().getAuthority();
        Map<String, Object> extra = new HashMap<>();
        extra.put("id user",id);
        extra.put("id res",idRestaurant);
        extra.put("dni",dni);
        extra.put("roles",roles);
        return Jwts.builder()
                .setSubject(mail)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 180))
                .addClaims(extra)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String getIdUserFromToken(String token) {
        Integer id = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().get("id user",Integer.class);
        return String.valueOf(id);
    }
    public String getIdRestaurantFromToken(String token) {
        Integer idRes = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().get("id res",Integer.class);
        return String.valueOf(idRes);
    }
    public String getRolesFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().get("roles",String.class);
    }
    public String getDniUsuarioFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        return (String) claims.get("dni");
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (SignatureException e) {
            logger.error("fail en la firma");
        }
        return false;
    }

    public String refreshToken(JwtResponseDto jwtResponseDto) throws ParseException {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtResponseDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtResponseDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String nombreUsuario = claims.getSubject();
            List<String> roles = claims.getStringListClaim("roles");
            //List<String> roles = (List<String>) claims.getClaim("roles");

            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim("roles", roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                    .compact();
        }
        return null;
    }



}
