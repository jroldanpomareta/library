package com.accenture.library.validation;

import com.accenture.library.dto.request.LoanRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Crea la clase pública LoanDatesValidator que implemente ConstraintValidator<ValidLoanDates, LoanRequestDTO>; implementa isValid para permitir valores nulos, dejando que @NotNull los valide, y comprobar que expectedReturnDate sea igual o posterior a loanDate; añade Javadoc en español a la clase y al método, utiliza los imports necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA
// Corrige LoanDatesValidator para que isValid devuelva true cuando el DTO sea null o cualquiera de las dos fechas sea null, dejando esas comprobaciones a @NotNull; cuando ambas fechas tengan valor, comprueba que expectedReturnDate sea igual o posterior a loanDate; añade Javadoc en español al método y conserva el resto de la clase. -> generado por IA
/**
 * Validador para las fechas de un préstamo.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public class LoanDatesValidator implements ConstraintValidator<ValidLoanDates, LoanRequestDTO> {

    /**
     * Valida que la fecha de devolución esperada sea igual o posterior a la fecha de préstamo.
     *
     * @param loanRequestDTO El objeto LoanRequestDTO a validar.
     * @param context        El contexto de validación.
     * @return true si las fechas son válidas o si el DTO es null; false en caso contrario.
     */
    @Override
    public boolean isValid(LoanRequestDTO loanRequestDTO, ConstraintValidatorContext context) {
        if (loanRequestDTO == null) {
            return true;
        }

        if (loanRequestDTO.getLoanDate() == null || loanRequestDTO.getExpectedReturnDate() == null) {
            return true;
        }

        return !loanRequestDTO.getExpectedReturnDate().isBefore(loanRequestDTO.getLoanDate());
    }
}