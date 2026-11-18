package com.accenture.library.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.accenture.library.dto.request.BookRequestDTO;

// Crea una clase pública BookStockValidatorTest en el paquete com.accenture.library.validation, preparada para pruebas unitarias con JUnit 5; añade los imports necesarios y Javadoc en español para la clase, pero no implementes todavía ningún método de prueba -> generado por IA
/**
 * Clase de pruebas unitarias para {@link BookStockValidator}.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
class BookStockValidatorTest {

    // Implementa un test llamado testAvailableStockGreaterThanTotalStock que cree
    // un BookRequestDTO con availableStock 15 y totalStock 10, invoque
    // BookStockValidator.isValid pasando el DTO y null como contexto y compruebe
    // con assertFalse que el resultado es inválido; añade el import necesario y
    // Javadoc en español -> generado por IA
    /**
     * Prueba que un BookRequestDTO con availableStock 15 y totalStock 10 sea
     * considerado inválido por el validador.
     */
    @Test
    void testAvailableStockGreaterThanTotalStock() {
        BookRequestDTO book = new BookRequestDTO();
        book.setAvailableStock(15);
        book.setTotalStock(10);

        BookStockValidator validator = new BookStockValidator();
        boolean isValid = validator.isValid(book, null);

        assertFalse(isValid);
    }

}