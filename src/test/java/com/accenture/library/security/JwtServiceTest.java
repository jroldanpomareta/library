
package com.accenture.library.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;

// Desarrolla la clase de pruebas unitarias JwtServiceTest utilizando JUnit 5 para comprobar el funcionamiento del servicio JwtService. Instancia el servicio directamente mediante su constructor, utilizando una clave secreta válida codificada en Base64 y un tiempo de expiración. Implementa las pruebas necesarias para verificar que los tokens JWT contienen correctamente el nombre de usuario en el claim subject y el rol en el claim role. No levantes el contexto de Spring ni utilices conexión a base de datos. Incluye Javadocs en español y nombres descriptivos para los métodos de prueba. -> generado por IA
/**
 * Clase de pruebas unitarias para el servicio JwtService.
 * Comprueba la generación y el contenido de los tokens JWT.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */

// Desarrolla la clase de pruebas unitarias JwtServiceTest utilizando JUnit 5.
// Instancia JwtService directamente mediante su constructor, sin levantar el
// contexto de Spring ni utilizar Mockito. Define una clave secreta válida
// codificada en Base64 y un tiempo de expiración para las pruebas. Comprueba
// que el token generado contiene correctamente el nombre de usuario en el
// subject y el rol en el claim role. Incluye Javadocs en español y comentarios
// Given, When y Then. -> generado por IA
class JwtServiceTest {

    private static final String SECRET = Base64.getEncoder()
            .encodeToString("01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8));

    private static final long EXPIRATION = 60000L;

    // Desarrolla un test que compruebe que generarToken crea un JWT que contiene
    // correctamente el nombre de usuario en el subject y el rol en el claim role.
    // Instancia JwtService con la clave secreta y expiración definidas. Genera un
    // token para el usuario javier con el rol ROLE_USER y utiliza extraerUsername y
    // extraerRol para comprobar ambos valores mediante assertEquals. Comprueba
    // también que el token es válido mediante validarToken. Incluye Given, When y
    // Then. -> generado por IA

    /**
     * Comprueba que el token generado contiene el usuario y el rol esperados.
     */
    @Test
    void generarToken_shouldContainUsernameAndRole_whenCredentialsAreProvided() {

        // Given
        JwtService jwtService = new JwtService(SECRET, EXPIRATION);
        String username = "javier";
        String role = SecurityConstants.ROLE_PREFIX + "USER";

        // When
        String token = jwtService.generarToken(username, role);

        // Then
        assertEquals(username, jwtService.extraerUsername(token));
        assertEquals(role, jwtService.extraerRol(token));
        assertTrue(jwtService.validarToken(token));
    }
}
