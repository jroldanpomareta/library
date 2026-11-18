package com.accenture.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.library.dto.error.ErrorResponse;
import com.accenture.library.dto.request.CategoryRequestDTO;
import com.accenture.library.dto.response.CategoryResponseDTO;
import com.accenture.library.service.interfaces.ICategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

// Crear la clase CategoryController en com.accenture.library.controller como controlador REST mediante @RestController y @RequestMapping("/api/v1/categories"). Inyectar ICategoryService mediante constructor. Implementar únicamente GET /api/v1/categories mediante @GetMapping, llamando a categoryService.listarCategorias() y devolviendo ResponseEntity<List<CategoryResponseDTO>> con HTTP 200. Añadir @Tag, @Operation y @ApiResponses documentando 200 y 401. Utilizar CategoryResponseDTO de com.accenture.library.dto.response. Añadir imports necesarios y Javadocs completos en español. No inventar métodos adicionales en ICategoryService. -> generado por IA
// Añadir @Tag de Swagger con el nombre "Categorías" y una descripción en español de las operaciones del controlador. Importar la anotación correspondiente. No modificar ningún otro elemento. -> generado por IA
/**
 * Controlador REST para gestionar las categorías de los libros.
 * Proporciona endpoints para listar, crear y eliminar categorías.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@io.swagger.v3.oas.annotations.tags.Tag(name = "Categorías", description = "Operaciones relacionadas con las categorías de los libros")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    /**
     * Servicio para gestionar las categorías.
     */
    private final ICategoryService categoryService;

    /**
     * Constructor del controlador.
     *
     * @param categoryService Servicio para gestionar las categorías.
     */
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Lista todas las categorías.
     *
     * @return ResponseEntity con la lista de categorías y el estado HTTP 200 OK.
     */
    @GetMapping
    @Operation(summary = "Listar categorías", description = "Obtiene una lista de todas las categorías")
            @ApiResponse(responseCode = "200", description = "Categorías obtenidas correctamente")
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<List<CategoryResponseDTO>> listarCategorias() {
        List<CategoryResponseDTO> categorias = categoryService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Implementar POST /api/v1/categories mediante @PostMapping. Recibir @Valid @RequestBody CategoryRequestDTO request, llamar a categoryService.crearCategoria(request) y devolver ResponseEntity<CategoryResponseDTO> con HTTP 201 CREATED. Documentar 201, 400 con ErrorResponse y 401 en Swagger. Añadir imports necesarios, @Operation, @ApiResponses y Javadocs completos con @param y @return. -> generado por IA
    /**
     * Crea una nueva categoría.
     *
     * @param request DTO que contiene los datos de la categoría a crear.
     * @return ResponseEntity con el DTO de la categoría creada y el estado HTTP 201 CREATED.
     */
    @PostMapping
    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría")
            @ApiResponse(responseCode = "201", description = "Categoría creada correctamente")
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<CategoryResponseDTO> crearCategoria(@Valid @RequestBody CategoryRequestDTO request) {
        CategoryResponseDTO categoria = categoryService.crearCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    /**
     * Elimina una categoría existente.
     *
     * @param id El ID de la categoría a eliminar.
     * @return ResponseEntity sin contenido y el estado HTTP 204 NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría existente")
            @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente")
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "409", description = "Conflicto al eliminar la categoría", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoryService.eliminarCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}