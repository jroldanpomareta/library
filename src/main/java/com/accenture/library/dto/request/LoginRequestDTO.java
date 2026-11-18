package com.accenture.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea la clase pública LoginRequestDTO en el paquete com.accenture.library.dto.request con los campos username y password, ambos String obligatorios con @NotBlank. Utiliza Lombok para generar getters, setters y constructor sin argumentos. Añade Javadocs en español para la clase y los campos. Incluye todos los imports necesarios y no utilices lambdas, referencias a métodos ni Streams. -> generado por IA
/**
 * DTO para la solicitud de inicio de sesión.
 * 
 * Contiene los campos necesarios para que un usuario pueda iniciar sesión en el sistema.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 * 
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDTO {

    /**
     * Nombre de usuario del usuario que intenta iniciar sesión.
     */
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;

    /**
     * Contraseña del usuario que intenta iniciar sesión.
     */
   
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

}
