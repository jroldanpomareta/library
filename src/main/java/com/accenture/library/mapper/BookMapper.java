package com.accenture.library.mapper;

import java.util.ArrayList;
import java.util.List;

// Regenera BookMapper con dos métodos estáticos: toResponseDTO(Book book), que copie id, title, isbn, publishedYear, availableStock y totalStock, asigne categoryId y categoryName desde la categoría si existe, y convierta la lista de autores mediante AuthorMapper.toResponseDTO; y toEntity(BookRequestDTO bookRequestDTO), que copie title, isbn, publishedYear, availableStock y totalStock; no intentes resolver categoryId ni authorIds mediante repositorios, porque esa responsabilidad corresponde al servicio; utiliza constructores sin argumentos, setters, ArrayList para inicializar las listas cuando corresponda, controla los valores null y las listas vacías, añade Javadoc en español a la clase y a todos sus métodos, utiliza los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA
// Corrige BookMapper para que toResponseDTO inicialice una List<AuthorResponseDTO> mediante ArrayList, recorra los autores de Book con un bucle clásico, añada cada conversión mediante AuthorMapper.toResponseDTO y finalmente asigne la lista con setAuthors; si Book no tiene autores, debe devolver una lista vacía; conserva el resto de métodos y campos sin cambios, añade los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA
import com.accenture.library.dto.request.BookRequestDTO;
import com.accenture.library.dto.response.AuthorResponseDTO;
import com.accenture.library.dto.response.BookResponseDTO;
import com.accenture.library.model.Author;
import com.accenture.library.model.Book;

/**
 * Clase que proporciona métodos para mapear entre la entidad Book y sus DTOs
 * correspondientes.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public class BookMapper {

    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private BookMapper() {
    }

    /**
     * Convierte una entidad Book en un DTO BookResponseDTO.
     *
     * @param book La entidad Book a convertir.
     * @return Un objeto BookResponseDTO con los datos de la entidad, o null si el
     *         parámetro es null.
     */
    public static BookResponseDTO toResponseDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setId(book.getId());
        responseDTO.setTitle(book.getTitle());
        responseDTO.setIsbn(book.getIsbn());
        responseDTO.setPublishedYear(book.getPublishedYear());
        responseDTO.setAvailableStock(book.getAvailableStock());
        responseDTO.setTotalStock(book.getTotalStock());

        if (book.getCategory() != null) {
            responseDTO.setCategoryId(book.getCategory().getId());
            responseDTO.setCategoryName(book.getCategory().getName());
        }

        if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
            List<AuthorResponseDTO> authors = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                authors.add(AuthorMapper.toResponseDTO(author));
            }
            responseDTO.setAuthors(authors);
        } else {
            responseDTO.setAuthors(new ArrayList<>());
        }

        return responseDTO;
    }

    /**
     * Convierte un DTO BookRequestDTO en una entidad Book.
     *
     * @param bookRequestDTO El DTO BookRequestDTO a convertir.
     * @return Una entidad Book con los datos del DTO, o null si el parámetro es
     *         null.
     */
    public static Book toEntity(BookRequestDTO bookRequestDTO) {
        if (bookRequestDTO == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPublishedYear(bookRequestDTO.getPublishedYear());
        book.setAvailableStock(bookRequestDTO.getAvailableStock());
        book.setTotalStock(bookRequestDTO.getTotalStock());
        return book;
    }
}