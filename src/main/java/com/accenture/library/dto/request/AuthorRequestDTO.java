package com.accenture.library.dto.request;

// Crea la clase pública AuthorRequestDTO en el paquete com.accenture.library.dto.request con los campos firstName y lastName de tipo String, obligatorios mediante @NotBlank y con longitud máxima de 100 caracteres, y nationality de tipo String opcional con longitud máxima de 100 caracteres; utiliza Lombok para generar getters, setters y constructor sin argumentos, añade Javadoc en español a la clase y a cualquier constructor explícito, utiliza únicamente los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa los datos de solicitud para crear o actualizar un autor.
 * Contiene información sobre el nombre, apellido y nacionalidad del autor.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorRequestDTO {
    /**
     * El nombre del autor.
     */
    @NotBlank
    @Size(max = 100)
    private String firstName;

    /**
     * El apellido del autor.
     */
    @NotBlank
    @Size(max = 100)
    private String lastName;

    /**
     * La nacionalidad del autor.
     */
    @Size(max = 100)
    private String nationality;

    /**
     * Constructor que inicializa los campos del autor.
     *
     * @param firstName   El nombre del autor.
     * @param lastName    El apellido del autor.
     * @param nationality La nacionalidad del autor.
     */
    public AuthorRequestDTO(String firstName, String lastName, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
    }
}