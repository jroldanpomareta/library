package com.accenture.library.dto.response;

// Crea la clase pública LoginResponseDTO en el paquete com.accenture.library.dto.response con un campo String token que contendrá el JWT generado tras un inicio de sesión correcto. Utiliza Lombok para generar getters, setters y constructor sin argumentos. Añade Javadocs en español para la clase y el campo. Incluye todos los imports necesarios y no utilices lambdas, referencias a métodos ni Streams. -> generado por IA
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para la respuesta de inicio de sesión.
 * 
 * Contiene el token JWT generado tras un inicio de sesión correcto.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 * 
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {

    /**
     * Token JWT generado tras un inicio de sesión correcto.
     */
    private String token;

}
