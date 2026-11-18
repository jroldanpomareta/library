package com.accenture.library.dto.request;

import java.time.LocalDate;

import com.accenture.library.validation.ValidLoanDates;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea la clase pública LoanRequestDTO en el paquete com.accenture.library.dto.request con los campos bookId Long obligatorio con @NotNull, loanDate LocalDate obligatorio con @NotNull y expectedReturnDate LocalDate obligatorio con @NotNull y formato JSON dd-MM-yyyy; utiliza Lombok para getters, setters y constructor sin argumentos, añade Javadoc en español a la clase y a cualquier constructor explícito, importa los tipos necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA
/**
 * Clase que representa los datos de solicitud para crear un préstamo.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
// Añade la anotación @ValidLoanDates a nivel de clase en LoanRequestDTO e importa com.accenture.library.validation.ValidLoanDates; conserva todos los campos, validaciones, formatos de fecha y anotaciones Lombok existentes. -> generado por IA
@ValidLoanDates
@Getter
@Setter
@NoArgsConstructor
public class LoanRequestDTO {
    @NotNull
    private Long bookId;

    @NotNull
    private LocalDate loanDate;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expectedReturnDate;
}