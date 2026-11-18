package com.accenture.library.service.impl;

// Crea desde cero CategoryServiceImpl en el paquete com.accenture.library.service.impl, sin repetir la declaración package, implementando exclusivamente com.accenture.library.service.interfaces.ICategoryService; utiliza ICategoryRepository de com.accenture.library.repository.interfaces, Category de com.accenture.library.model, CategoryRequestDTO de com.accenture.library.dto.request, CategoryResponseDTO de com.accenture.library.dto.response, CategoryMapper de com.accenture.library.mapper y las excepciones ResourceNotFoundException y ResourceConflictException de com.accenture.library.exception; utiliza exclusivamente los tres métodos de la interfaz con sus firmas exactas: List<CategoryResponseDTO> listarCategorias(), CategoryResponseDTO crearCategoria(CategoryRequestDTO categoryRequestDTO) y void eliminarCategoria(Long id); anota la clase con @Service y @Transactional de org.springframework.transaction.annotation.Transactional; declara ICategoryRepository como atributo final e inyéctalo mediante un único constructor público sin @Autowired; NO inyectes CategoryMapper porque sus métodos toResponseDTO() y toEntity() son estáticos; en listarCategorias() recupera las entidades mediante findAll(), crea una ArrayList de CategoryResponseDTO y convierte cada categoría mediante CategoryMapper.toResponseDTO() utilizando exclusivamente un bucle for clásico; en crearCategoria() comprueba primero mediante existsByName(categoryRequestDTO.getName()) si ya existe una categoría con ese nombre, lanza ResourceConflictException si está duplicada, convierte el DTO mediante CategoryMapper.toEntity(), guarda la entidad mediante save() y devuelve CategoryMapper.toResponseDTO() de la entidad guardada; en eliminarCategoria() busca mediante findById(id), comprueba el Optional con isEmpty() y get() mediante condicionales clásicos, lanza ResourceNotFoundException si no existe, comprueba que category.getBooks() esté vacío y lanza ResourceConflictException si tiene libros asociados, y finalmente elimina mediante delete(category); añade imports completos y correctos; incluye Javadoc en español en la clase, constructor y los tres métodos sobrescritos, con @param, @return y @throws cuando correspondan; no inventes clases, interfaces, paquetes, métodos ni firmas; no añadas métodos alternativos, TODO, UnsupportedOperationException ni implementaciones duplicadas; no utilices lambdas, referencias a métodos, Streams ni métodos funcionales que reciban lambdas. -> generado por IA

import com.accenture.library.dto.request.CategoryRequestDTO;
import com.accenture.library.dto.response.CategoryResponseDTO;
import com.accenture.library.exception.ResourceConflictException;
import com.accenture.library.exception.ResourceNotFoundException;
import com.accenture.library.mapper.CategoryMapper;
import com.accenture.library.model.Category;
import com.accenture.library.repository.interfaces.ICategoryRepository;
import com.accenture.library.service.interfaces.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de categorías.
 * Proporciona métodos para listar, crear y eliminar categorías en la biblioteca.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    /**
     * Repositorio de categorías para acceder a la base de datos.
     */
    private final ICategoryRepository categoryRepository;

    /**
     * Constructor de la clase CategoryServiceImpl.
     *
     * @param categoryRepository El repositorio de categorías a inyectar.
     */
    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Lista todas las categorías.
     *
     * @return Una lista de objetos CategoryResponseDTO.
     */
    @Override
    public List<CategoryResponseDTO> listarCategorias() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> responseDTOs = new ArrayList<>();
        for (Category category : categories) {
            responseDTOs.add(CategoryMapper.toResponseDTO(category));
        }
        return responseDTOs;
    }

    /**
     * Crea una nueva categoría.
     *
     * @param categoryRequestDTO Los datos de la categoría a crear.
     * @return El objeto CategoryResponseDTO de la categoría creada.
     */
    @Override
    public CategoryResponseDTO crearCategoria(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRepository.existsByName(categoryRequestDTO.getName())) {
            throw new ResourceConflictException("Ya existe una categoría con ese nombre.");
        }
        Category category = CategoryMapper.toEntity(categoryRequestDTO);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toResponseDTO(savedCategory);
    }

    /**
     * Elimina una categoría por su ID.
     *
     * @param id El ID de la categoría a eliminar.
     */
    @Override
    public void eliminarCategoria(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró la categoría con el ID especificado.");
        }
        Category category = optionalCategory.get();
        if (!category.getBooks().isEmpty()) {
            throw new ResourceConflictException("La categoría tiene libros asociados y no puede ser eliminada.");
        }
        categoryRepository.delete(category);
    }
}