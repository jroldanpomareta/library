package com.accenture.library.validation;

// Crea la clase pública BookStockValidator que implemente ConstraintValidator<ValidBookStock, BookRequestDTO>; implementa el método isValid utilizando variables Integer para availableStock y totalStock, devuelve true si el DTO o cualquiera de los dos valores es null y, en caso contrario, comprueba que availableStock sea menor o igual que totalStock; añade Javadoc en español a la clase y al método, utiliza los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.accenture.library.dto.request.BookRequestDTO;

/**
 * Validador para la anotación @ValidBookStock que verifica que el stock disponible de un libro no supere su stock total.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */ 
public class BookStockValidator implements ConstraintValidator<ValidBookStock, BookRequestDTO> {

    /**
     * Valida que el stock disponible de un libro no supere su stock total.
     *
     * @param bookRequestDTO el DTO de solicitud del libro a validar
     * @param context el contexto de validación
     * @return true si el DTO es null, si cualquiera de los valores es null o si availableStock es menor o igual que totalStock; false en caso contrario
     */
    @Override
    public boolean isValid(BookRequestDTO bookRequestDTO, ConstraintValidatorContext context) {
        if (bookRequestDTO == null) {
            return true;
        }

        Integer availableStock = bookRequestDTO.getAvailableStock();
        Integer totalStock = bookRequestDTO.getTotalStock();

        if (availableStock == null || totalStock == null) {
            return true;
        }

        return availableStock <= totalStock;
    }
}