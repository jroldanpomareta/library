package com.accenture.library.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;

// Crea la anotación personalizada ValidBookStock para validar que el stock disponible de un libro no supere su stock total; debe poder aplicarse a nivel de clase, integrarse con Jakarta Bean Validation mediante @Constraint, utilizar la clase BookStockValidator como validador, incluir los atributos message, groups y payload con sus valores estándar, añadir Javadoc en español y no emplear lambdas, referencias a métodos ni Streams. -> generado por IA
/**
 * Anotación personalizada para validar que el stock disponible de un libro no supere su stock total.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookStockValidator.class)
@Documented
public @interface ValidBookStock {
    String message() default "El stock disponible no puede superar el stock total";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}