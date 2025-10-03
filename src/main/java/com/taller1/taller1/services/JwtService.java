package com.taller1.taller1.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.taller1.taller1.models.Empleado;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String claveSecreta = "clave-super-secreta";
    private final long expiracion = 3600000;

    public String generarToken(Empleado empleado) {
        return Jwts.builder()
                .setSubject(empleado.getNom())
                .claim("rol", empleado.getCargo().getCargo())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(Keys.hmacShaKeyFor(claveSecreta.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String obtenerUsuario(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(claveSecreta.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean esTokenValido(String token, Empleado empleado) {
        return obtenerUsuario(token).equals(empleado.getNom()) && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(claveSecreta.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}