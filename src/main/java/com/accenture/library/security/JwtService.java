package com.accenture.library.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

// Crea JwtService para gestionar JWT usando JJWT 0.13.0. Inyecta app.jwt.secret y app.jwt.expiration-ms mediante constructor. Decodifica la clave Base64 y crea una SecretKey con Keys.hmacShaKeyFor. Implementa generarToken(String username, String rol), extraerUsername(String token), extraerRol(String token) y validarToken(String token). Genera tokens con subject, claim role, issuedAt y expiration; firma con signWith(key). Valida mediante Jwts.parser().verifyWith(key).build().parseSignedClaims(token). Captura JwtException e IllegalArgumentException en validarToken y devuelve false. Añade Javadocs en español a la clase y todos sus métodos. No uses APIs obsoletas de JJWT. -> generado por IA
/**
 * Servicio para gestionar tokens JWT.
 * 
 * Este servicio permite generar, extraer y validar tokens JWT.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Service
public class JwtService {

    /**
     * Clave secreta utilizada para firmar y verificar los tokens JWT.
     */
    private final SecretKey key;
    /**
     * Tiempo de expiración del token en milisegundos.
     */
    private final long expiration;

    /**
     * Constructor de JwtService.
     *
     * @param secret La clave secreta codificada en Base64 para firmar los tokens JWT.
     * @param expiration El tiempo de expiración del token en milisegundos.
     */
    public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long expiration) {
        this.key = Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(secret));
        this.expiration = expiration;
    }

    /**
     * Genera un token JWT con el nombre de usuario y el rol especificados.
     *
     * @param username El nombre de usuario.
     * @param role El rol del usuario.
     * @return El token JWT generado.
     */
    public String generarToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    /**
     * Extrae el nombre de usuario del token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario extraído del token.
     */
    public String extraerUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * Extrae el rol del token JWT.
     *
     * @param token El token JWT.
     * @return El rol extraído del token.
     */
    public String extraerRol(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    /**
     * Valida el token JWT.
     *
     * @param token El token JWT a validar.
     * @return true si el token es válido, false en caso contrario.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
