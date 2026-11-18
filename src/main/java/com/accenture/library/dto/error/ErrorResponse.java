package com.accenture.library.dto.error;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Regenera ErrorResponse manteniendo el paquete com.accenture.library.dto.error y los cinco campos actuales; utiliza exclusivamente @Getter, @Setter y @NoArgsConstructor de Lombok, elimina @Builder y @AllArgsConstructor, conserva el Javadoc de la clase en español y no añadas lógica de negocio. -> generado por IA
/**
 * DTO de respuesta de errores que contiene información sobre un error ocurrido en la aplicación.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    /**
     * La fecha y hora del error.
     */
    private LocalDateTime timestamp;

    /**
     * El código de estado del error.
     */
    private int status;

    /**
     * El tipo de error.
     */
    private String error;

    /**
     * El mensaje del error.
     */
    private String message;

    /**
     * La ruta donde ocurrió el error.
     */
    private String path;
}