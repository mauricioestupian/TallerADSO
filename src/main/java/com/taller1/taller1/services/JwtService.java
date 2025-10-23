package com.taller1.taller1.services;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.taller1.taller1.models.Empleado;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private Key claveSecreta() {
        // El algoritmo HMAC-SHA256 exige una clave de al menos 256 bits (32 bytes)
        // es decir 32 caracteres si usamos solo caracteres ASCII
        String clave = "Sena_Adso_2025_Taller_1_Proyecto_Final_Clave_Secreta";
        return Keys.hmacShaKeyFor(clave.getBytes(StandardCharsets.UTF_8));
    }

    private final long expiracion = 3600000;

    public String generarToken(Empleado empleado) {
        return Jwts.builder()
                .setSubject(empleado.getUsername())
                .claim("rol", "ROLE_" + empleado.getCargo().getCargo().toUpperCase().replace(" ", "_"))
                .claim("id", empleado.getId())
                .claim("nombreCompleto", empleado.getNom() + " " + empleado.getApe())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(claveSecreta(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String obtenerUsuario(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(claveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean esTokenValido(String token, Empleado empleado) {
        return obtenerUsuario(token).equals(empleado.getUsername()) && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(claveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}