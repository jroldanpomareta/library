package com.accenture.library.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea la clase pública BookResponseDTO en el paquete com.accenture.library.dto.response con los campos id Long, title String, isbn String, publishedYear Integer, availableStock Integer, totalStock Integer, categoryId Long, categoryName String y authors List<AuthorResponseDTO>; utiliza Lombok para getters, setters y constructor sin argumentos, importa los tipos necesarios, añade Javadoc en español a la clase y a cualquier constructor explícito y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA

/**
 * Clase que representa los datos de respuesta de un libro.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String isbn;
    private Integer publishedYear;
    private Integer availableStock;
    private Integer totalStock;
    private Long categoryId;
    private String categoryName;
    private List<AuthorResponseDTO> authors;
}