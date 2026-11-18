package com.accenture.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.library.dto.error.ErrorResponse;
import com.accenture.library.dto.request.LoanRequestDTO;
import com.accenture.library.dto.response.LoanResponseDTO;
import com.accenture.library.service.interfaces.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

// Crear LoanController en com.accenture.library.controller con @RestController y @RequestMapping("/api/v1/loans"). Inyectar ILoanService mediante constructor. Implementar únicamente GET /api/v1/loans con @GetMapping, llamar a loanService.listarPrestamos() y devolver ResponseEntity<List<LoanResponseDTO>> con HTTP 200. Añadir @Tag, @Operation y @ApiResponses documentando 200 y 401. Importar los tipos necesarios y añadir Javadocs completos en español. No implementar otros endpoints todavía ni modificar otras clases. -> generado por IA
/**
 * Controlador REST para gestionar los préstamos de libros.
 *
 * Este controlador proporciona endpoints para listar, obtener, crear y actualizar préstamos de libros en la biblioteca.
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@io.swagger.v3.oas.annotations.tags.Tag(name = "Préstamos", description = "Operaciones relacionadas con los préstamos de libros")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    /**
     * Servicio para gestionar los préstamos.
     */
    private final ILoanService loanService;

    /**
     * Constructor del controlador.
     *
     * @param loanService Servicio para gestionar los préstamos.
     */
    public LoanController(ILoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Lista todos los préstamos.
     *
     * @return ResponseEntity con la lista de préstamos y el estado HTTP 200 OK.
     */
    @GetMapping
    @Operation(summary = "Listar préstamos", description = "Obtiene una lista de todos los préstamos")
            @ApiResponse(responseCode = "200", description = "Préstamos obtenidos correctamente")
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<List<LoanResponseDTO>> listarPrestamos() {
        List<LoanResponseDTO> prestamos = loanService.listarPrestamos();
        return ResponseEntity.ok(prestamos);
    }

    /**
     * Obtiene un préstamo por su ID.
     *
     * @param id El ID del préstamo a obtener.
     * @return ResponseEntity con el préstamo obtenido y el estado HTTP 200 OK.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener préstamo por ID", description = "Obtiene un préstamo específico por su ID")
            @ApiResponse(responseCode = "200", description = "Préstamo obtenido correctamente")
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<LoanResponseDTO> obtenerPrestamoPorId(@PathVariable Long id) {
        LoanResponseDTO prestamo = loanService.obtenerPrestamoPorId(id);
        return ResponseEntity.ok(prestamo);
    }

    // Implementar POST /api/v1/loans mediante @PostMapping. Recibir @Valid @RequestBody LoanRequestDTO request, llamar a loanService.crearPrestamo(request) y devolver ResponseEntity<LoanResponseDTO> con HTTP 201 CREATED. Documentar 201, 400 con ErrorResponse, 409 con ErrorResponse y 401 en Swagger. Añadir imports necesarios, @Operation, @ApiResponses y Javadocs completos con @param y @return. -> generado por IA
    /**
     * Crea un nuevo préstamo.
     *
     * @param request DTO con los datos del préstamo a crear.
     * @return ResponseEntity con el préstamo creado y el estado HTTP 201 CREATED.
     */
    @PostMapping
    @Operation(summary = "Crear préstamo", description = "Crea un nuevo préstamo")
            @ApiResponse(responseCode = "201", description = "Préstamo creado correctamente")
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "409", description = "Conflicto al crear el préstamo", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<LoanResponseDTO> crearPrestamo(@Valid @RequestBody LoanRequestDTO request) {
        LoanResponseDTO prestamo = loanService.crearPrestamo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
    }

    // Implementar PUT /api/v1/loans/{id}/return mediante @PutMapping("/{id}/return"). Recibir @PathVariable Long id, llamar a loanService.devolverLibro(id) y devolver ResponseEntity<LoanResponseDTO> mediante ResponseEntity.ok(). Documentar 200, 404 y 409 con ErrorResponse, además de 401 en Swagger. Añadir imports necesarios, @Operation, @ApiResponses y Javadocs completos con @param y @return. -> generado por IA
    /**
     * Marca un préstamo como devuelto.
     *
     * @param id El ID del préstamo a marcar como devuelto.
     * @return ResponseEntity con el préstamo actualizado y el estado HTTP 200 OK.
     */
    @PutMapping("/{id}/return")
    @Operation(summary = "Marcar préstamo como devuelto", description = "Marca un préstamo específico como devuelto")
            @ApiResponse(responseCode = "200", description = "Préstamo actualizado correctamente")
            @ApiResponse(responseCode = "404", description = "Préstamo no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "409", description = "Conflicto al marcar el préstamo como devuelto", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    public ResponseEntity<LoanResponseDTO> marcarPrestamoComoDevuelto(@PathVariable Long id) {
        LoanResponseDTO prestamo = loanService.devolverLibro(id);
        return ResponseEntity.ok(prestamo);
    }

}
