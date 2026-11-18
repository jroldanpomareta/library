package com.accenture.library.exception;

// Crea ResourceConflictException como excepción personalizada que extienda RuntimeException, con un único constructor público que reciba String message y ejecute super(message); añade Javadoc en español a la clase y al constructor, sin imports innecesarios. -> generado por IA

/**
 * Excepción personalizada que indica que hay un conflicto con un recurso.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public class ResourceConflictException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param message El mensaje de la excepción.
     */
    public ResourceConflictException(String message) {
        super(message);
    }
}