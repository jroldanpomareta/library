package com.accenture.library.service.impl;

// Crear un test unitario para LoanServiceImpl con JUnit 5 y Mockito que compruebe que crearPrestamo registra correctamente un préstamo con estado ACTIVE y descuenta una unidad del stock disponible del libro. Aplicar Given/When/Then con comentarios en cada fase y nombrar el método crearPrestamo_shouldCrearPrestamoYReducirStock_whenHayStockDisponible. Utilizar @ExtendWith(MockitoExtension.class), @Mock e @InjectMocks, sin levantar el contexto de Spring ni conectar con una base de datos real. No utilizar Streams ni lambdas en el test. Documentar la clase y el método con Javadoc en español. No utilizar CascadeType.ALL ni CascadeType.REMOVE. Mantener el código sencillo, legible y comprobar mediante verificaciones de Mockito el guardado del libro y del préstamo -> generado por IA

import com.accenture.library.dto.request.LoanRequestDTO;
import com.accenture.library.dto.response.LoanResponseDTO;
import com.accenture.library.enums.LoanStatus;
import com.accenture.library.exception.ResourceConflictException;
import com.accenture.library.model.Book;
import com.accenture.library.model.Loan;
import com.accenture.library.repository.interfaces.IBookRepository;
import com.accenture.library.repository.interfaces.ILoanRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test unitario para la clase LoanServiceImpl.
 * Comprueba el comportamiento del método crearPrestamo.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    private ILoanRepository loanRepository;

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    // Crear el test
    // crearPrestamo_shouldCrearPrestamoYReducirStock_whenHayStockDisponible. En
    // Given preparar un libro con id 1 y availableStock igual a 5, simular
    // bookRepository.findById para devolver ese libro, preparar un LoanRequestDTO
    // válido y configurar loanRepository.save para devolver un préstamo guardado
    // con estado ACTIVE y asociado al libro. En When ejecutar
    // loanService.crearPrestamo con el DTO. En Then comprobar que el resultado no
    // es null, que el stock del libro ha pasado de 5 a 4, que el préstamo tiene
    // estado ACTIVE y que se ha llamado una vez a bookRepository.save y a
    // loanRepository.save. Utilizar exclusivamente JUnit 5 y Mockito, sin Spring
    // Context ni base de datos real. No utilizar lambdas, Streams, method
    // references ni expresiones funcionales. No utilizar @BeforeEach si no es
    // necesario. Documentar el método con Javadoc en español -> generado por IA
    /**
     * Test para crear un préstamo y reducir el stock cuando hay stock disponible.
     * 
     * @throws Exception si ocurre algún error durante la ejecución del test.
     */
    @Test
    void crearPrestamo_shouldCrearPrestamoYReducirStock_whenHayStockDisponible() throws Exception {
        // Given
        Book book = new Book();
        book.setId(1L);
        book.setAvailableStock(5);

        LoanRequestDTO loanRequest = new LoanRequestDTO();
        loanRequest.setBookId(1L);
        loanRequest.setLoanDate(LocalDate.now());
        loanRequest.setExpectedReturnDate(LocalDate.now().plusDays(14));

        // no utilizar lambdas ni Streams, simular el comportamiento de los repositorios
        // con Mockito
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan loan = invocation.getArgument(0);
            loan.setId(1L);
            return loan;
        });

        // When
        LoanResponseDTO result = loanService.crearPrestamo(loanRequest);

        // Then
        assertNotNull(result);
        assertEquals(4, book.getAvailableStock());
        assertEquals(LoanStatus.ACTIVE, result.getStatus());
        verify(bookRepository, times(1)).save(book);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    // Crear un test unitario llamado
    // crearPrestamo_shouldLanzarConflictException_whenNoHayStockDisponible. En
    // Given preparar un libro con id 1 y availableStock igual a 0, simular
    // bookRepository.findById para devolverlo y preparar un LoanRequestDTO válido.
    // En When ejecutar loanService.crearPrestamo. En Then comprobar que se lanza
    // ResourceConflictException y que no se invoca ningún guardado en
    // bookRepository ni loanRepository. Utilizar JUnit 5 y Mockito, aplicar
    // Given/When/Then con comentarios, documentar el método con Javadoc en español
    // y no utilizar Streams. No conectar con una base de datos ni levantar el
    // contexto de Spring -> generado por IA
    /**
     * Test para verificar que se lanza ResourceConflictException cuando no hay
     * stock disponible al crear un préstamo.
     * 
     */
    
    @Test
    void crearPrestamo_shouldLanzarConflictException_whenNoHayStockDisponible() {
        // Given
        Book book = new Book();
        book.setId(1L);
        book.setAvailableStock(0);

        LoanRequestDTO loanRequest = new LoanRequestDTO();
        loanRequest.setBookId(1L);
        loanRequest.setLoanDate(LocalDate.now());
        loanRequest.setExpectedReturnDate(LocalDate.now().plusDays(14));

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // When
        // se dejan las lambdas porque están permitidas en los test según el pdf y llevo toda
        // la tarde y no hay manera de que me lo dé sin ellas
        assertThrows(ResourceConflictException.class, () -> {
            loanService.crearPrestamo(loanRequest);
        });

        // Then
        verify(bookRepository, times(0)).save(any(Book.class));
        verify(loanRepository, times(0)).save(any(Loan.class));

    }

    // Crear un test unitario llamado
    // devolverLibro_shouldCambiarEstadoADevuelto_whenPrestamoActivo. En Given
    // preparar un libro con id 1 y stock disponible 4, y un préstamo con id 1,
    // estado ACTIVE, fecha de préstamo anterior a hoy, fecha prevista de devolución
    // futura, actualReturnDate null y asociado al libro. Simular
    // loanRepository.findById para devolver el préstamo y configurar
    // loanRepository.save para devolver el préstamo recibido. En When ejecutar
    // loanService.devolverLibro(1L). En Then comprobar que el resultado no es null,
    // que el estado del préstamo es RETURNED, que actualReturnDate no es null y que
    // se ha invocado una vez bookRepository.save y loanRepository.save. Utilizar
    // JUnit 5 y Mockito, Given/When/Then, Javadoc en español, sin Streams y sin
    // conexión a base de datos ni contexto Spring -> generado por IA
    /**
     * Test para verificar que un préstamo activo cambia su estado a RETURNED y se
     * asigna la fecha real de devolución.
     * 
     */
    @Test
    void devolverLibro_shouldCambiarEstadoADevuelto_whenPrestamoActivo() {
        // Given
        Book book = new Book();
        book.setId(1L);
        book.setAvailableStock(4);

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setLoanDate(LocalDate.now().minusDays(1));
        loan.setExpectedReturnDate(LocalDate.now().plusDays(14));
        loan.setActualReturnDate(null);
        loan.setBook(book);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        // Cambiar el tipo de la variable result a LoanResponseDTO porque
        // loanService.devolverLibro(1L) devuelve un DTO de respuesta y no una entidad
        // Loan. Mantener el resto del método sin modificaciones -> generado por IA
        LoanResponseDTO result = loanService.devolverLibro(1L);

        // Then
        assertNotNull(result);
        assertEquals(LoanStatus.RETURNED, result.getStatus());
        // Comprobar que se ha asignado la fecha real de devolución -> generado por IA
        assertNotNull(result.getActualReturnDate());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    // Crear un test unitario llamado devolverLibro_shouldIncrementarStock_whenPrestamoActivo. En Given preparar un libro con id 1 y availableStock igual a 4, y un préstamo con id 1, estado ACTIVE y asociado al libro. Simular loanRepository.findById para devolver el préstamo y configurar loanRepository.save para devolver el préstamo recibido. En When ejecutar loanService.devolverLibro(1L). En Then comprobar que el stock del libro ha aumentado exactamente de 4 a 5 y que bookRepository.save se ha invocado una vez. Utilizar JUnit 5 y Mockito, aplicar Given/When/Then, documentar el método con Javadoc en español y no utilizar Streams. No conectar con una base de datos ni levantar el contexto de Spring -> generado por IA
    /**
     * Test para verificar que al devolver un préstamo activo, el stock del libro
     * asociado se incrementa en una unidad.
     * 
     */
    @Test
    void devolverLibro_shouldIncrementarStock_whenPrestamoActivo() {
        // Given
        Book book = new Book();
        book.setId(1L);
        book.setAvailableStock(4);

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setLoanDate(LocalDate.now().minusDays(1));
        loan.setExpectedReturnDate(LocalDate.now().plusDays(14));
        loan.setActualReturnDate(null);
        loan.setBook(book);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        loanService.devolverLibro(1L);

        // Then
        assertEquals(5, book.getAvailableStock());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    // Crear un test unitario llamado devolverLibro_shouldLanzarConflictException_whenPrestamoYaDevuelto. En Given preparar un libro con id 1 y un préstamo con id 1, estado RETURNED, actualReturnDate con una fecha anterior a hoy y asociado al libro. Simular loanRepository.findById para devolver el préstamo. En When ejecutar loanService.devolverLibro(1L). En Then comprobar que se lanza ResourceConflictException y que no se invoca bookRepository.save ni loanRepository.save. Utilizar assertThrows de JUnit 5, Mockito, Given/When/Then y documentar el método con Javadoc en español. No utilizar Streams. No conectar con una base de datos ni levantar el contexto de Spring -> generado por IA
    /**
     * Test para verificar que se lanza ResourceConflictException cuando se intenta
     * devolver un préstamo que ya ha sido devuelto.
     * 
     */
    @Test
    void devolverLibro_shouldLanzarConflictException_whenPrestamoYaDevuelto() {
        // Given
        Book book = new Book();
        book.setId(1L);
        book.setAvailableStock(4);

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setStatus(LoanStatus.RETURNED);
        loan.setLoanDate(LocalDate.now().minusDays(1));
        loan.setExpectedReturnDate(LocalDate.now().plusDays(14));
        loan.setActualReturnDate(LocalDate.now().minusDays(1));
        loan.setBook(book);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        // When
        assertThrows(ResourceConflictException.class, () -> loanService.devolverLibro(1L));

        // Then
        verify(bookRepository, times(0)).save(any(Book.class));
        verify(loanRepository, times(0)).save(any(Loan.class));
    }

}