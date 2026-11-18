package com.accenture.library.validation;

// Crea la anotación personalizada ValidLoanDates para validar las fechas de LoanRequestDTO a nivel de clase; intégrala con Jakarta Bean Validation mediante @Constraint, utiliza LoanDatesValidator como validador, incluye los atributos message, groups y payload con sus valores estándar, añade Javadoc en español y los imports necesarios, sin emplear lambdas, referencias a métodos ni Streams. -> generado por IA
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;

/**
 * Anotación personalizada para validar las fechas de un préstamo.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = LoanDatesValidator.class)
public @interface ValidLoanDates {
    /**
     * Mensaje de error por defecto.
     */
    String message() default "Las fechas del préstamo no son válidas";

    /**
     * Grupos de validación.
     */
    Class<?>[] groups() default {};

    /**
     * Payload de la anotación.
     */
    Class<? extends Payload>[] payload() default {};
}