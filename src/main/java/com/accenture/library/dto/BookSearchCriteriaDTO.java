package com.accenture.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea la clase pública BookSearchCriteriaDTO en el paquete com.accenture.library.dto.request para agrupar los parámetros de búsqueda y paginación de libros; declara los campos privados page (int), size (int), sort (String), title (String), author (String), isbn (String), categoryId (Long), authorId (Long) y available (Boolean); añade constructor, getters y setters siguiendo el estilo de los DTO existentes en el proyecto; añade Javadoc completo en español para la clase, constructor y métodos públicos; no utilices records, lambdas, Streams ni referencias a métodos. -> generado por IA
/**
 * DTO que agrupa los criterios de búsqueda y paginación de libros.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter 
@NoArgsConstructor 
public class BookSearchCriteriaDTO {

    private int page = 0;
    private int size = 20;
    private String sort = "title,asc";
    private String title;
    private String author;
    private String isbn;
    private Long categoryId;
    private Long authorId;
    private Boolean available;

}