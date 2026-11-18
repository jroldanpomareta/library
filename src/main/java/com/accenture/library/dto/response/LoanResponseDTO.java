package com.accenture.library.dto.response;

import java.time.LocalDate;

import com.accenture.library.enums.LoanStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea la clase pública LoanResponseDTO en el paquete com.accenture.library.dto.response con los campos id Long, bookId Long, bookTitle String, loanDate LocalDate, expectedReturnDate LocalDate, actualReturnDate LocalDate y status LoanStatus; aplica @JsonFormat(pattern = "dd-MM-yyyy") a los tres campos de fecha, utiliza Lombok para getters, setters y constructor sin argumentos, añade Javadoc en español a la clase y a cualquier constructor explícito, importa los tipos necesarios y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA
/**
 * Clase que representa la respuesta de un préstamo.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class LoanResponseDTO {
    private Long id;
    private Long bookId;
    private String bookTitle;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate loanDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expectedReturnDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate actualReturnDate;
    private LoanStatus status;
}