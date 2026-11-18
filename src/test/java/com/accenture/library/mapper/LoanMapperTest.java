package com.accenture.library.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.accenture.library.dto.response.LoanResponseDTO;
import com.accenture.library.model.Book;
import com.accenture.library.model.Loan;
import com.accenture.library.enums.LoanStatus;

// Prepara una clase de pruebas unitarias JUnit 5 para LoanMapper, no utilices Spring ni Mockito y sigue la estructura del proyecto -> generado por IA
/**
 * Clase de pruebas unitarias para {@link LoanMapper}.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
class LoanMapperTest {

    // Añade un test que compruebe que un préstamo ACTIVE cuya expectedReturnDate
    // sea anterior a hoy se convierte en un DTO con estado OVERDUE, siguiendo la
    // estructura y convenciones de los tests existentes -> generado por IA
    // Modifica este test para que utilice fechas dinámicas basadas en
    // LocalDate.now(): establece expectedReturnDate como
    // LocalDate.now().minusDays(1) y loanDate como LocalDate.now().minusDays(15);
    // conserva el estado inicial ACTIVE, el resto de datos y todas las aserciones
    // existentes; el resultado esperado debe seguir siendo OVERDUE; mantén el
    // estilo Given/When/Then y no utilices lambdas ni Streams. -> generado por IA
    /**
     * Prueba que un Loan activo con expectedReturnDate anterior a hoy se mapea a un
     * LoanResponseDTO con estado OVERDUE.
     */
    @Test
    void toResponseDTO_shouldReturnOverdue_whenExpectedReturnDateIsBeforeToday() {
        Loan loan = new Loan();
        loan.setId(2L);
        loan.setLoanDate(LocalDate.now().minusDays(15));
        loan.setExpectedReturnDate(LocalDate.now().minusDays(1));
        loan.setActualReturnDate(null);
        loan.setStatus(LoanStatus.ACTIVE);

        Book book = new Book();
        book.setId(2L);
        book.setTitle("Refactoring");

        loan.setBook(book);

        LoanResponseDTO responseDTO = LoanMapper.toResponseDTO(loan);

        assertNotNull(responseDTO);
        assertEquals(2L, responseDTO.getId());
        assertEquals(LocalDate.now().minusDays(15), responseDTO.getLoanDate());
        assertEquals(LocalDate.now().minusDays(1), responseDTO.getExpectedReturnDate());
        assertNull(responseDTO.getActualReturnDate());
        assertEquals(LoanStatus.OVERDUE, responseDTO.getStatus());
        assertEquals(2L, responseDTO.getBookId());
        assertEquals("Refactoring", responseDTO.getBookTitle());
    }
}