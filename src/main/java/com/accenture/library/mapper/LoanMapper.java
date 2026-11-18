package com.accenture.library.mapper;

import java.time.LocalDate;

// Crea LoanMapper con dos métodos estáticos: toResponseDTO(Loan loan), que convierta una entidad Loan en LoanResponseDTO copiando id, loanDate, expectedReturnDate, actualReturnDate y status, y obteniendo bookId y bookTitle desde la relación Book si existe; y toEntity(LoanRequestDTO loanRequestDTO), que copie loanDate y expectedReturnDate; no resuelvas bookId mediante repositorios ni asignes la relación Book, porque esa responsabilidad corresponde al servicio; controla los valores null, utiliza constructores sin argumentos y setters, añade los imports necesarios y Javadoc en español a la clase y todos sus métodos, y no emplees lambdas, referencias a métodos ni Streams. -> generado por IA

import com.accenture.library.dto.request.LoanRequestDTO;
import com.accenture.library.dto.response.LoanResponseDTO;
import com.accenture.library.enums.LoanStatus;
import com.accenture.library.model.Loan;

/**
 * Clase que proporciona métodos para mapear entre la entidad Loan y sus DTOs
 * correspondientes.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public class LoanMapper {

    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private LoanMapper() {
    }

    /**
     * Convierte una entidad Loan en un DTO LoanResponseDTO.
     *
     * @param loan La entidad Loan a convertir.
     * @return Un objeto LoanResponseDTO con los datos de la entidad, o null si el
     *         parámetro es null.
     */
    public static LoanResponseDTO toResponseDTO(Loan loan) {
        if (loan == null) {
            return null;
        }
        LoanResponseDTO responseDTO = new LoanResponseDTO();
        responseDTO.setId(loan.getId());
        responseDTO.setLoanDate(loan.getLoanDate());
        responseDTO.setExpectedReturnDate(loan.getExpectedReturnDate());
        responseDTO.setActualReturnDate(loan.getActualReturnDate());
        LoanStatus status = loan.getStatus();

        if (status == LoanStatus.ACTIVE
                && loan.getExpectedReturnDate() != null
                && loan.getExpectedReturnDate().isBefore(LocalDate.now())) {
            status = LoanStatus.OVERDUE;
        }

        responseDTO.setStatus(status);

        if (loan.getBook() != null) {
            responseDTO.setBookId(loan.getBook().getId());
            responseDTO.setBookTitle(loan.getBook().getTitle());
        }

        return responseDTO;
    }

    /**
     * Convierte un DTO LoanRequestDTO en una entidad Loan.
     *
     * @param loanRequestDTO El DTO LoanRequestDTO a convertir.
     * @return Una entidad Loan con los datos del DTO, o null si el parámetro es
     *         null.
     */
    public static Loan toEntity(LoanRequestDTO loanRequestDTO) {
        if (loanRequestDTO == null) {
            return null;
        }
        Loan loan = new Loan();
        loan.setLoanDate(loanRequestDTO.getLoanDate());
        loan.setExpectedReturnDate(loanRequestDTO.getExpectedReturnDate());
        return loan;
    }
}