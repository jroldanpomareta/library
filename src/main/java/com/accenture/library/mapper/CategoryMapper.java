package com.accenture.library.mapper;

import com.accenture.library.dto.request.CategoryRequestDTO;
import com.accenture.library.dto.response.CategoryResponseDTO;
import com.accenture.library.model.Category;

// Crea la clase pública CategoryMapper con dos métodos estáticos: toResponseDTO(Category category), que convierta una entidad Category en CategoryResponseDTO, y toEntity(CategoryRequestDTO categoryRequestDTO), que convierta un DTO en una entidad Category; utiliza constructores sin argumentos y setters para asignar los campos, comprueba si el parámetro recibido es null y devuelve null en ese caso, no intentes obtener un id desde CategoryRequestDTO porque no contiene ese campo, añade Javadoc en español a la clase y a todos sus métodos, y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA


/**
 * Clase que proporciona métodos para mapear entre la entidad Category y sus DTOs correspondientes.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public class CategoryMapper {

    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private CategoryMapper() {
    }

    /**
     * Convierte una entidad Category en un DTO CategoryResponseDTO.
     *
     * @param category La entidad Category a convertir.
     * @return Un objeto CategoryResponseDTO con los datos de la entidad, o null si el parámetro es null.
     */
    public static CategoryResponseDTO toResponseDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setDescription(category.getDescription());
        return responseDTO;
    }

    /**
     * Convierte un DTO CategoryRequestDTO en una entidad Category.
     *
     * @param categoryRequestDTO El DTO CategoryRequestDTO a convertir.
     * @return Una entidad Category con los datos del DTO, o null si el parámetro es null.
     */
    public static Category toEntity(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO == null) {
            return null;
        }
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        return category;
    }
}