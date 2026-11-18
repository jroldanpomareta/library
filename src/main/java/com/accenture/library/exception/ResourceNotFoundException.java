package com.accenture.library.exception;

// Crea ResourceNotFoundException como excepción personalizada que extienda RuntimeException, incluye un constructor que reciba un mensaje y lo transmita a la clase padre mediante super, añade Javadoc en español a la clase y al constructor, e incluye los imports necesarios. -> generado por IA
/**
 * Excepción personalizada que indica que un recurso no fue encontrado.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param message El mensaje de la excepción.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}