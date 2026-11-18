package com.accenture.library.service.impl;

// Crea desde cero AuthorServiceImpl en el paquete com.accenture.library.service.impl, implementando exclusivamente com.accenture.library.service.interfaces.IAuthorService; utiliza Author de com.accenture.library.model, IAuthorRepository de com.accenture.library.repository.interfaces, AuthorRequestDTO de com.accenture.library.dto.request, AuthorResponseDTO de com.accenture.library.dto.response, AuthorMapper de com.accenture.library.mapper y ResourceNotFoundException y ResourceConflictException de com.accenture.library.exception; anota la clase con @Service y @Transactional de org.springframework.transaction.annotation.Transactional, declara IAuthorRepository como atributo final e inyéctalo mediante un único constructor público sin @Autowired; implementa exactamente las cinco firmas de IAuthorService: List<AuthorResponseDTO> listarAutores(), AuthorResponseDTO obtenerAutorPorId(Long id), AuthorResponseDTO crearAutor(AuthorRequestDTO authorRequestDTO), AuthorResponseDTO actualizarAutor(Long id, AuthorRequestDTO authorRequestDTO) y void eliminarAutor(Long id); listarAutores() debe recuperar todos los autores mediante findAll(), convertirlos con AuthorMapper.toResponseDTO() y devolver una ArrayList utilizando un bucle for clásico; obtenerAutorPorId() debe buscar mediante findById(), guardar el resultado en Optional<Author>, comprobar isEmpty() y utilizar get() mediante condicionales clásicos, lanzar ResourceNotFoundException si no existe y devolver el DTO mediante AuthorMapper.toResponseDTO(); crearAutor() debe convertir el DTO mediante AuthorMapper.toEntity(), guardar mediante save() y devolver el DTO de la entidad guardada; actualizarAutor() debe buscar el autor mediante findById(), comprobar el Optional con isEmpty() y get(), lanzar ResourceNotFoundException si no existe, actualizar firstName, lastName y nationality mediante sus setters, guardar mediante save() y devolver el DTO de la entidad guardada; eliminarAutor() debe buscar mediante findById(), comprobar el Optional con isEmpty() y get(), lanzar ResourceNotFoundException si no existe, comprobar que author.getBooks() esté vacío, lanzar ResourceConflictException si tiene libros asociados y eliminar mediante delete(author) únicamente cuando no tenga libros; utiliza exclusivamente los métodos existentes de IAuthorRepository y los métodos estáticos de AuthorMapper, no inyectes el mapper ni inventes métodos; añade todos los imports necesarios y Javadoc completo en español a la clase, constructor y los cinco métodos, incluyendo @param, @return y @throws cuando correspondan; no añadas métodos alternativos, TODO ni UnsupportedOperationException; queda terminantemente prohibido utilizar lambdas, referencias a métodos, Streams, Optional.map(), Optional.filter(), Optional.orElseThrow(), Optional.ifPresent() o cualquier método funcional que reciba lambdas; utiliza exclusivamente bucles y condicionales clásicos. -> generado por IA

import com.accenture.library.dto.request.AuthorRequestDTO;
import com.accenture.library.dto.response.AuthorResponseDTO;
import com.accenture.library.exception.ResourceConflictException;
import com.accenture.library.exception.ResourceNotFoundException;
import com.accenture.library.mapper.AuthorMapper;
import com.accenture.library.model.Author;
import com.accenture.library.repository.interfaces.IAuthorRepository;
import com.accenture.library.service.interfaces.IAuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de autores.
 * Proporciona métodos para listar, obtener, crear, actualizar y eliminar autores en la biblioteca.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Service
@Transactional
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepository authorRepository;

    /**
     * Constructor de la implementación del servicio de autores.
     * 
     * @param authorRepository Repositorio de autores.
     */
    public AuthorServiceImpl(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Lista todos los autores.
     * 
     * @return Lista de DTOs de autores.
     */
    @Override
    public List<AuthorResponseDTO> listarAutores() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponseDTO> responseDTOs = new ArrayList<>();
        for (Author author : authors) {
            responseDTOs.add(AuthorMapper.toResponseDTO(author));
        }
        return responseDTOs;
    }

    /**
     * Obtiene un autor por su ID.
     * 
     * @param id ID del autor a obtener.
     * @return DTO del autor obtenido.
     * @throws ResourceNotFoundException Si el autor no existe.
     */
    @Override
    public AuthorResponseDTO obtenerAutorPorId(Long id) throws ResourceNotFoundException {
        java.util.Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            throw new ResourceNotFoundException("Autor no encontrado");
        }
        return AuthorMapper.toResponseDTO(optionalAuthor.get());
    }

    /**
     * Crea un nuevo autor.
     * 
     * @param authorRequestDTO DTO con los datos del nuevo autor.
     * @return DTO del autor creado.
     */
    @Override
    public AuthorResponseDTO crearAutor(AuthorRequestDTO authorRequestDTO) {
        Author author = AuthorMapper.toEntity(authorRequestDTO);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.toResponseDTO(savedAuthor);
    }

    /**
     * Actualiza un autor existente.
     * 
     * @param id ID del autor a actualizar.
     * @param authorRequestDTO DTO con los nuevos datos del autor.
     * @return DTO del autor actualizado.
     * @throws ResourceNotFoundException Si el autor no existe.
     */
    @Override
    public AuthorResponseDTO actualizarAutor(Long id, AuthorRequestDTO authorRequestDTO) throws ResourceNotFoundException {
        java.util.Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            throw new ResourceNotFoundException("Autor no encontrado");
        }
        Author author = optionalAuthor.get();
        author.setFirstName(authorRequestDTO.getFirstName());
        author.setLastName(authorRequestDTO.getLastName());
        author.setNationality(authorRequestDTO.getNationality());
        Author updatedAuthor = authorRepository.save(author);
        return AuthorMapper.toResponseDTO(updatedAuthor);
    }

    /**
     * Elimina un autor por su ID.
     * 
     * @param id ID del autor a eliminar.
     * @throws ResourceNotFoundException Si el autor no existe.
     * @throws ResourceConflictException Si el autor tiene libros asociados.
     */
    @Override
    public void eliminarAutor(Long id) throws ResourceNotFoundException, ResourceConflictException {
        java.util.Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            throw new ResourceNotFoundException("Autor no encontrado");
        }
        Author author = optionalAuthor.get();
        if (!author.getBooks().isEmpty()) {
            throw new ResourceConflictException("No se puede eliminar un autor con libros asociados");
        }
        authorRepository.delete(author);
    }
}