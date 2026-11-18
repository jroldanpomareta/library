package com.accenture.library.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea la clase pública AuthorResponseDTO en el paquete com.accenture.library.dto.response con los campos id de tipo Long, firstName, lastName y nationality de tipo String; utiliza Lombok para generar getters, setters y constructor sin argumentos, añade Javadoc en español a la clase y a cualquier constructor explícito, utiliza únicamente los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA

/**
 * Clase que representa los datos de respuesta de un autor.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
}