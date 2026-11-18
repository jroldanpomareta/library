
package com.accenture.library.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

// Crea un DTO llamado BookPageResponseDTO en el paquete dto.response que represente una respuesta paginada de libros. Debe contener los campos content (List<BookResponseDTO>), pageNumber (int), pageSize (int), totalElements (long), totalPages (int) y last (boolean). Utiliza Lombok @Getter y @NoArgsConstructor, incluye un constructor público que inicialice todos los campos y añade Javadoc en español para la clase, sus atributos y el constructor. No utilices @Data, @Builder ni @AllArgsConstructor. -> generado por IA
/**
 * DTO que representa la respuesta paginada de libros.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@NoArgsConstructor
public class BookPageResponseDTO {

    private List<BookResponseDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    /**
     * Constructor de la respuesta paginada.
     *
     * @param content Lista de libros de la página actual.
     * @param pageNumber Número de página actual.
     * @param pageSize Tamaño de página aplicado.
     * @param totalElements Número total de libros.
     * @param totalPages Número total de páginas.
     * @param last Indica si es la última página.
     */
    public BookPageResponseDTO(
            List<BookResponseDTO> content,
            int pageNumber,
            int pageSize,
            long totalElements,
            int totalPages,
            boolean last) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
}
