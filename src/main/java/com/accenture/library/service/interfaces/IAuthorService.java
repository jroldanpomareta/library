package com.accenture.library.service.interfaces;

import java.util.List;

import com.accenture.library.dto.request.AuthorRequestDTO;
import com.accenture.library.dto.response.AuthorResponseDTO;

// Crea la interfaz pública IAuthorService en el paquete com.accenture.library.service.interfaces con los métodos para listar todos los autores, obtener un autor por su identificador, crear un autor, actualizar un autor y eliminarlo por su identificador; utiliza List<AuthorResponseDTO> para listar, Long para los identificadores, AuthorRequestDTO como entrada y AuthorResponseDTO como respuesta; incluye Javadoc en español en la interfaz y en todos sus métodos, documenta parámetros y retornos, y no utilices lambdas, referencias a métodos ni Streams. -> generado por IA
/**
 * Interfaz que define los servicios relacionados con los autores en la biblioteca.
 * Proporciona métodos para gestionar los autores en la biblioteca.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public interface IAuthorService {
    /**
     * Lista todos los autores.
     *
     * @return Una lista de objetos AuthorResponseDTO que representan los autores.
     */
    List<AuthorResponseDTO> listarAutores();

    /**
     * Obtiene un autor por su ID.
     *
     * @param id El ID del autor a obtener.
     * @return El objeto AuthorResponseDTO que representa el autor obtenido.
     */
    AuthorResponseDTO obtenerAutorPorId(Long id);

    /**
     * Crea un nuevo autor.
     *
     * @param authorRequestDTO El objeto que contiene los datos del autor a crear.
     * @return El objeto AuthorResponseDTO que representa el autor creado.
     */
    AuthorResponseDTO crearAutor(AuthorRequestDTO authorRequestDTO);

    /**
     * Actualiza un autor existente.
     *
     * @param id El ID del autor a actualizar.
     * @param authorRequestDTO El objeto que contiene los nuevos datos del autor.
     * @return El objeto AuthorResponseDTO que representa el autor actualizado.
     */
    AuthorResponseDTO actualizarAutor(Long id, AuthorRequestDTO authorRequestDTO);

    /**
     * Elimina un autor por su ID.
     *
     * @param id El ID del autor a eliminar.
     */
    void eliminarAutor(Long id);
}