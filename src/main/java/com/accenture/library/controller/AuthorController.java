package com.accenture.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.library.dto.error.ErrorResponse;
import com.accenture.library.dto.request.AuthorRequestDTO;
import com.accenture.library.dto.response.AuthorResponseDTO;
import com.accenture.library.service.interfaces.IAuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// Crear la clase AuthorController en el paquete com.accenture.library.controller como controlador REST mediante @RestController y @RequestMapping("/api/v1/authors"). Inyectar IAuthorService mediante constructor. Implementar los siguientes cinco endpoints utilizando exclusivamente los métodos existentes en IAuthorService y los DTOs del proyecto: 1) GET /api/v1/authors para listar todos los autores y devolver ResponseEntity<List<AuthorResponseDTO>>; 2) GET /api/v1/authors/{id} para obtener un autor por su identificador y devolver ResponseEntity<AuthorResponseDTO>; 3) POST /api/v1/authors para crear un autor, recibir @Valid @RequestBody AuthorRequestDTO y devolver HTTP 201 CREATED con el DTO; 4) PUT /api/v1/authors/{id} para actualizar un autor, recibir @PathVariable Long id y @Valid @RequestBody AuthorRequestDTO y devolver HTTP 200 OK con el DTO; 5) DELETE /api/v1/authors/{id} para eliminar un autor y devolver HTTP 204 NO_CONTENT sin body. Añadir @Tag de Swagger a la clase y @Operation y @ApiResponses a cada endpoint, con textos en español. Documentar estos códigos: listado 200; consulta 200 y 404; creación 201 y 400; actualización 200, 400 y 404; eliminación 204, 404 y 409. Añadir 401 a todos los endpoints protegidos y 403 únicamente al DELETE. Incluir @Content(schema = @Schema(implementation = ErrorResponse.class)) en todas las respuestas 400, 404 y 409. Importar ErrorResponse desde com.accenture.library.dto.error.ErrorResponse. Añadir Javadoc completo en español a la clase, constructor y todos los métodos, incluyendo @param y @return cuando corresponda. No implementar lógica de negocio en el controlador. No utilizar lambdas, Streams, method references, CascadeType.ALL ni CascadeType.REMOVE. No modificar otras clases. -> generado por IA
/**
 * Controlador REST para gestionar operaciones relacionadas con los autores.
 * Proporciona endpoints para listar, obtener, crear, actualizar y eliminar autores.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Tag(name = "Autores", description = "Operaciones relacionadas con los autores")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;

    /**
     * Constructor de la clase AuthorController.
     *
     * @param authorService Servicio de autores inyectado mediante constructor.
     */
    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    // Implementar el endpoint GET /api/v1/authors mediante @GetMapping. Llamar a
    // authorService.listarAutores() y devolver
    // ResponseEntity<List<AuthorResponseDTO>> mediante ResponseEntity.ok(). Añadir
    // @Operation y @ApiResponses documentando HTTP 200 y 401, con textos en
    // español. Añadir Javadoc completo con @return. No modificar el constructor ni
    // otros elementos existentes. -> generado por IA
    /**
     * Endpoint para listar todos los autores.
     *
     * @return ResponseEntity con la lista de autores y el estado HTTP 200 OK.
     */
    @GetMapping
    @Operation(summary = "Listar autores", description = "Lista todos los autores")
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida correctamente")
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<List<AuthorResponseDTO>> listarAutores() {
        List<AuthorResponseDTO> autores = authorService.listarAutores();
        return ResponseEntity.ok(autores);
    }

    // Implementar el endpoint GET /api/v1/authors/{id} mediante @GetMapping("/{id}"). Recibir el identificador con @PathVariable Long id, llamar a authorService.obtenerAutorPorId(id) y devolver ResponseEntity<AuthorResponseDTO> mediante ResponseEntity.ok(). Documentar HTTP 200, 404 con ErrorResponse y 401 en Swagger. Añadir Javadoc completo con @param y @return. -> generado por IA
    /**
     * Endpoint para obtener un autor por su ID.
     *
     * @param id El ID del autor a obtener.
     * @return ResponseEntity con el autor obtenido y el estado HTTP 200 OK.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener autor por ID", description = "Obtiene un autor específico por su ID")
            @ApiResponse(responseCode = "200", description = "Autor obtenido correctamente")
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<AuthorResponseDTO> obtenerAutorPorId(@PathVariable Long id) {
        AuthorResponseDTO autor = authorService.obtenerAutorPorId(id);
        return ResponseEntity.ok(autor);
    }

    // Implementar POST /api/v1/authors mediante @PostMapping. Recibir @Valid @RequestBody AuthorRequestDTO, llamar a authorService.crearAutor(request) y devolver ResponseEntity<AuthorResponseDTO> con HTTP 201 CREATED. Documentar 201, 400 con ErrorResponse y 401 en Swagger. Añadir los imports necesarios y Javadoc completo con @param y @return. -> generado por IA
    /**
     * Endpoint para crear un nuevo autor.
     *
     * @param request Los datos del autor a crear.
     * @return ResponseEntity con el autor creado y el estado HTTP 201 CREATED.
     */
    @PostMapping
    @Operation(summary = "Crear autor", description = "Crea un nuevo autor")
            @ApiResponse(responseCode = "201", description = "Autor creado correctamente")
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<AuthorResponseDTO> crearAutor(@Valid @RequestBody AuthorRequestDTO request) {
        AuthorResponseDTO autor = authorService.crearAutor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }

    // Implementar PUT /api/v1/authors/{id} mediante @PutMapping("/{id}"). Recibir @PathVariable Long id y @Valid @RequestBody AuthorRequestDTO request. Llamar a authorService.actualizarAutor(id, request) y devolver ResponseEntity<AuthorResponseDTO> mediante ResponseEntity.ok(). Documentar 200, 400 y 404 con ErrorResponse, además de 401. Añadir Javadoc completo con @param y @return. -> generado por IA
    /**
     * Endpoint para actualizar un autor existente.
     *
     * @param id El ID del autor a actualizar.
     * @param request Los datos del autor a actualizar.
     * @return ResponseEntity con el autor actualizado y el estado HTTP 200 OK.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar autor", description = "Actualiza un autor existente")
            @ApiResponse(responseCode = "200", description = "Autor actualizado correctamente")
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<AuthorResponseDTO> actualizarAutor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDTO request) {
        AuthorResponseDTO autor = authorService.actualizarAutor(id, request);
        return ResponseEntity.ok(autor);
    }

    // Implementar DELETE /api/v1/authors/{id} mediante @DeleteMapping("/{id}"). Recibir @PathVariable Long id, llamar a authorService.eliminarAutor(id) y devolver ResponseEntity<Void> con HTTP 204 NO_CONTENT. Documentar 204, 404 y 409 con ErrorResponse, además de 401 y 403. Añadir Javadoc completo con @param y @return. -> generado por IA
    /**
     * Endpoint para eliminar un autor existente.
     *
     * @param id El ID del autor a eliminar.
     * @return ResponseEntity sin contenido y el estado HTTP 204 NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar autor", description = "Elimina un autor existente")
            @ApiResponse(responseCode = "204", description = "Autor eliminado correctamente")
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "409", description = "Conflicto al eliminar el autor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Long id) {
        authorService.eliminarAutor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}