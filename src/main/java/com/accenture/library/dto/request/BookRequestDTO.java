package com.accenture.library.dto.request;

import jakarta.validation.constraints.Max;

// Crea la clase pública BookRequestDTO en el paquete com.accenture.library.dto.request con los campos title String obligatorio con @NotBlank y @Size(max = 150), isbn String obligatorio con @NotBlank y @Size(max = 20), publishedYear Integer opcional, availableStock Integer obligatorio con @NotNull y @Min(0), totalStock Integer obligatorio con @NotNull y @Min(0), categoryId Long opcional y authorIds List<Long> opcional; añade las validaciones necesarias, utiliza Lombok para getters, setters y constructor sin argumentos, Javadoc en español para la clase y cualquier constructor explícito, imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.accenture.library.validation.ValidBookStock;

/**
 * Clase que representa los datos de solicitud para crear o actualizar un libro.
 * Contiene información sobre el título, ISBN, año de publicación, stock
 * disponible,
 * stock total, categoría y autores del libro.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
// Añade la anotación @ValidBookStock a nivel de clase en BookRequestDTO e
// importa com.accenture.library.validation.ValidBookStock; conserva todo lo
// demás sin modificaciones. -> generado por IA
@ValidBookStock
@Getter
@Setter
@NoArgsConstructor
public class BookRequestDTO {
    /**
     * El título del libro.
     */
    @NotBlank
    @Size(max = 150)
    private String title;

    /**
     * El ISBN del libro.
     */
    @NotBlank
    @Size(max = 20)
    private String isbn;

    /**
     * El año de publicación del libro.
     */
    @Min(value = 1000, message = "El año de publicación debe ser como mínimo 1000.")
    @Max(value = 2100, message = "El año de publicación no puede superar 2100.")
    private Integer publishedYear;

    /**
     * El stock disponible del libro.
     */
    @NotNull
    @Min(0)
    private Integer availableStock;

    /**
     * El stock total del libro.
     */
    @NotNull
    @Min(1)
    private Integer totalStock;

    /**
     * El ID de la categoría del libro.
     */
    private Long categoryId;

    /**
     * La lista de IDs de los autores del libro.
     */
    private List<Long> authorIds;
}