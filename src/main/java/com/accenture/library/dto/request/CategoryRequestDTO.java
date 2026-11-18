package com.accenture.library.dto.request;

// Crea la clase pública CategoryRequestDTO en el paquete com.accenture.library.dto.request con los campos name de tipo String y description de tipo String; aplica las validaciones obligatorias de name con @NotBlank y @Size(max = 100), y de description con @Size(max = 500); utiliza Lombok para generar getters, setters y constructor sin argumentos, añade Javadoc en español a la clase y documenta los campos, utiliza únicamente los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa los datos de solicitud para crear o actualizar una categoría.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryRequestDTO {
    /**
     * El nombre de la categoría.
     */
    @NotBlank
    @Size(max = 100)
    private String name;

    /**
     * La descripción de la categoría.
     */
    @Size(max = 500)
    private String description;

    /**
     * Constructor que inicializa los campos de la categoría.
     *
     * @param name        El nombre de la categoría.
     * @param description La descripción de la categoría.
     */
    public CategoryRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}