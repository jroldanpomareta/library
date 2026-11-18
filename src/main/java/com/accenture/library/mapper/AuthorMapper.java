package com.accenture.library.mapper;

// Crea la clase pública AuthorMapper con dos métodos estáticos: toResponseDTO(Author author), que convierta una entidad Author en AuthorResponseDTO, y toEntity(AuthorRequestDTO authorRequestDTO), que convierta un DTO en una entidad Author; utiliza constructores sin argumentos y setters para asignar id, firstName, lastName y nationality cuando corresponda, comprueba si el parámetro recibido es null y devuelve null en ese caso, añade Javadoc en español a la clase y a todos sus métodos, utiliza los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA

import com.accenture.library.dto.request.AuthorRequestDTO;
import com.accenture.library.dto.response.AuthorResponseDTO;
import com.accenture.library.model.Author;

/**
 * Clase que proporciona métodos para mapear entre la entidad Author y sus DTOs
 * correspondientes.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public class AuthorMapper {

    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private AuthorMapper() {
    }

    /**
     * Convierte una entidad Author en un DTO AuthorResponseDTO.
     *
     * @param author La entidad Author a convertir.
     * @return Un objeto AuthorResponseDTO con los datos de la entidad, o null si el
     *         parámetro es null.
     */
    public static AuthorResponseDTO toResponseDTO(Author author) {
        if (author == null) {
            return null;
        }
        AuthorResponseDTO responseDTO = new AuthorResponseDTO();
        responseDTO.setId(author.getId());
        responseDTO.setFirstName(author.getFirstName());
        responseDTO.setLastName(author.getLastName());
        responseDTO.setNationality(author.getNationality());
        return responseDTO;
    }

    /**
     * Convierte un DTO AuthorRequestDTO en una entidad Author.
     *
     * @param authorRequestDTO El DTO AuthorRequestDTO a convertir.
     * @return Una entidad Author con los datos del DTO, o null si el parámetro es
     *         null.
     */
    public static Author toEntity(AuthorRequestDTO authorRequestDTO) {
        if (authorRequestDTO == null) {
            return null;
        }
        Author author = new Author();
        author.setFirstName(authorRequestDTO.getFirstName());
        author.setLastName(authorRequestDTO.getLastName());
        author.setNationality(authorRequestDTO.getNationality());
        return author;
    }
}