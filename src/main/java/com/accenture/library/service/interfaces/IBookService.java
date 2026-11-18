package com.accenture.library.service.interfaces;

// Genera la interfaz pública IBookService en este paquete con cinco métodos: Page<BookResponseDTO> listarLibros(int page, int size, String title, String author, String isbn, Long categoryId), BookResponseDTO obtenerLibroPorId(Long id), BookResponseDTO crearLibro(BookRequestDTO bookRequestDTO), BookResponseDTO actualizarLibro(Long id, BookRequestDTO bookRequestDTO) y void eliminarLibro(Long id); importa Page y las clases necesarias, incluye Javadoc en español en la interfaz y en cada método, documenta todos los parámetros y retornos, y no utilices lambdas, referencias a métodos ni Streams. -> generado por IA
// Actualiza IBookService conservando el paquete, los imports y los métodos existentes; modifica exclusivamente la firma de listarLibros para que devuelva Page<BookResponseDTO> y reciba, en este orden, int page, int size, String sort, String title, String author, String isbn, Long categoryId, Long authorId y Boolean available; estos parámetros deben permitir gestionar paginación, ordenación y todos los filtros de libros; conserva exactamente las firmas de los demás métodos de la interfaz, añade los imports necesarios y Javadoc completo en español para la interfaz y todos sus métodos, sin inventar operaciones adicionales. -> generado por IA
    import org.springframework.data.domain.Page;

import com.accenture.library.dto.BookSearchCriteriaDTO;
import com.accenture.library.dto.request.BookRequestDTO;
import com.accenture.library.dto.response.BookResponseDTO;

    /**
     * Interfaz que define los servicios relacionados con los libros en la biblioteca.
     * Proporciona métodos para gestionar los libros en la biblioteca.
     * 
     * @author Javier Roldán Pomareta
     * @version 1.0
     */
    public interface IBookService {
    /**
     * Lista los libros de la biblioteca según los criterios de búsqueda y paginación proporcionados.
     *
     * @param criterios Objeto que contiene los criterios de búsqueda y paginación.
     * @return Una página de objetos BookResponseDTO que representan los libros encontrados.
     */
    Page<BookResponseDTO> listarLibros(BookSearchCriteriaDTO criterios);
    
    /**
     * Obtiene un libro por su ID.
     *
     * @param id El ID del libro a obtener.
     * @return El objeto BookResponseDTO que representa el libro obtenido.
     */
    BookResponseDTO obtenerLibroPorId(Long id);

    /**
     * Crea un nuevo libro.
     *
     * @param bookRequestDTO El objeto que contiene los datos del libro a crear.
     * @return El objeto BookResponseDTO que representa el libro creado.
     */
    BookResponseDTO crearLibro(BookRequestDTO bookRequestDTO);

    /**
     * Actualiza un libro existente.
     *
     * @param id El ID del libro a actualizar.
     * @param bookRequestDTO El objeto que contiene los nuevos datos del libro.
     * @return El objeto BookResponseDTO que representa el libro actualizado.
     */
    BookResponseDTO actualizarLibro(Long id, BookRequestDTO bookRequestDTO);

    /**
     * Elimina un libro por su ID.
     *
     * @param id El ID del libro a eliminar.
     */
    void eliminarLibro(Long id);
}