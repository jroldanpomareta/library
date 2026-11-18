package com.accenture.library.service.interfaces;

import java.util.List;

import com.accenture.library.dto.request.CategoryRequestDTO;
import com.accenture.library.dto.response.CategoryResponseDTO;

// Genera la interfaz pública ICategoryService en este paquete con tres métodos: List<CategoryResponseDTO> listarCategorias(), CategoryResponseDTO crearCategoria(CategoryRequestDTO categoryRequestDTO) y void eliminarCategoria(Long id); importa List, incluye Javadoc en español en la interfaz y en cada método, documenta parámetros y retornos, y no utilices lambdas, referencias a métodos ni Streams. -> generado por IA

/**
 * Interfaz que define los servicios relacionados con las categorías en la biblioteca.
 * Proporciona métodos para gestionar las categorías en la biblioteca.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public interface ICategoryService {
    /**
     * Lista todas las categorías.
     *
     * @return Una lista de objetos CategoryResponseDTO que representan las categorías.
     */
    List<CategoryResponseDTO> listarCategorias();

    /**
     * Crea una nueva categoría.
     *
     * @param categoryRequestDTO El objeto que contiene los datos de la categoría a crear.
     * @return El objeto CategoryResponseDTO que representa la categoría creada.
     */
    CategoryResponseDTO crearCategoria(CategoryRequestDTO categoryRequestDTO);

    /**
     * Elimina una categoría por su ID.
     *
     * @param id El ID de la categoría a eliminar.
     */
    void eliminarCategoria(Long id);
}