package com.accenture.library.security;

// Crea la clase final SecurityConstants en el paquete com.accenture.library.security para centralizar las constantes de seguridad ROLE_PREFIX con valor "ROLE_", BEARER_PREFIX con valor "Bearer ", 
//  con valor "ROLE_ADMIN" y ROLE_USER con valor "ROLE_USER"; declara todos los campos como public static final String, añade Javadoc completo en español para la clase y el constructor privado, evita la instanciación de la clase y no utilices lambdas, Streams ni records. -> generado por IA
/**
 * Centraliza las constantes utilizadas en la configuración de seguridad.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public final class SecurityConstants {

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    /**
     * Constructor privado para evitar la instanciación de esta clase.
     */
    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }
}