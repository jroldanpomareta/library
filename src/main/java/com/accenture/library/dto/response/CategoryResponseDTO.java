package com.accenture.library.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea la clase pública CategoryResponseDTO en el paquete com.accenture.library.dto.response con los campos id de tipo Long, name de tipo String y description de tipo String; utiliza Lombok para generar getters, setters y constructor sin argumentos, añade Javadoc en español a la clase y documenta los campos, utiliza únicamente los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA
/**
 * Clase que representa los datos de respuesta de una categoría.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
}