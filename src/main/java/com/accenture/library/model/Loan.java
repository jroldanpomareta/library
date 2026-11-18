package com.accenture.library.model;

import java.time.LocalDate;

import com.accenture.library.enums.LoanStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Crea desde cero la entidad JPA Loan asociada a la tabla "loan", con id Long autogenerado mediante IDENTITY, loanDate LocalDate obligatorio, expectedReturnDate LocalDate obligatorio, actualReturnDate LocalDate opcional y status de tipo LoanStatus almacenado mediante @Enumerated(EnumType.STRING), incluyendo los valores ACTIVE, RETURNED y OVERDUE en el enum, aunque OVERDUE nunca se persiste en MySQL; establece una relación ManyToOne obligatoria con Book mediante @JoinColumn(name = "book_id", nullable = false), optional = false y FetchType.LAZY; no añadas relaciones inversas ni cascadas; utiliza Lombok selectivamente (@Getter, @Setter y @NoArgsConstructor), no incluyas constructores con parámetros innecesarios, evita @Data, lambdas, referencias a métodos, Streams y Records; respeta exactamente el esquema SQL e incluye Javadoc completo en español. -> generado por IA

//pon el javadoc en español -> generado por IA
/**
 * Representa un préstamo de libro en el sistema de biblioteca.
 * Esta entidad está asociada a la tabla "loan" en la base de datos.
 * Incluye campos para la fecha del préstamo, la fecha esperada de devolución,
 * la fecha real de devolución y el estado del préstamo.
 * La relación con el libro se define como ManyToOne obligatoria.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "loan_date", nullable = false)

    private LocalDate loanDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "expected_return_date", nullable = false)

    private LocalDate expectedReturnDate;
    @Column(name = "actual_return_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate actualReturnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}