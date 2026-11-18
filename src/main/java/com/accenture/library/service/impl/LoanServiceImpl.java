package com.accenture.library.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.library.dto.request.LoanRequestDTO;
import com.accenture.library.dto.response.LoanResponseDTO;
import com.accenture.library.enums.LoanStatus;
import com.accenture.library.exception.ResourceConflictException;
import com.accenture.library.exception.ResourceNotFoundException;
import com.accenture.library.mapper.LoanMapper;
import com.accenture.library.model.Book;
import com.accenture.library.model.Loan;
import com.accenture.library.repository.interfaces.IBookRepository;
import com.accenture.library.repository.interfaces.ILoanRepository;
import com.accenture.library.service.interfaces.ILoanService;

// Crea la clase pública LoanServiceImpl en el paquete com.accenture.library.service.impl que implemente ILoanService; añade las anotaciones @Service y @Transactional; declara los atributos privados y finales necesarios de tipo ILoanRepository e IBookRepository; crea un constructor público que reciba ambas dependencias y las asigne mediante this; añade todos los imports necesarios y Javadoc completo en español para la clase y el constructor; deja únicamente la estructura de la clase, sin implementar todavía los métodos de la interfaz. -> generado por IA

/**
 * Implementación de la interfaz ILoanService.
 * Proporciona los métodos para gestionar los préstamos de libros en la biblioteca.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Service
@Transactional
public class LoanServiceImpl implements ILoanService {

    private final ILoanRepository loanRepository;
    private final IBookRepository bookRepository;

    /**
     * Constructor de la clase LoanServiceImpl.
     *
     * @param loanRepository Repositorio de préstamos.
     * @param bookRepository Repositorio de libros.
     */
    public LoanServiceImpl(ILoanRepository loanRepository, IBookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }
  
    // Implementa listarPrestamos() para recuperar todos los préstamos mediante loanRepository.findAll(), crear una ArrayList<LoanResponseDTO>, recorrer los préstamos con un bucle for clásico y convertir cada uno mediante LoanMapper.toResponseDTO(loan); si el préstamo tiene estado ACTIVE y expectedReturnDate es anterior a LocalDate.now(), establece OVERDUE únicamente en el DTO de respuesta, sin modificar ni guardar la entidad; devuelve la lista y añade Javadoc completo en español; utiliza bucles clásicos y no emplees lambdas, Streams, referencias a métodos ni métodos funcionales de colecciones. -> generado por IA
    /**
     * Lista todos los préstamos.
     *
     * @return Una lista de objetos LoanResponseDTO.
     */ 
	@Override
	public List<LoanResponseDTO> listarPrestamos() {
		List<Loan> loans = loanRepository.findAll();
        List<LoanResponseDTO> responseDTOs = new ArrayList<>();
        for (Loan loan : loans) {
            LoanResponseDTO dto = LoanMapper.toResponseDTO(loan);
            if (loan.getStatus() == LoanStatus.ACTIVE && loan.getExpectedReturnDate().isBefore(LocalDate.now())) {
                dto.setStatus(LoanStatus.OVERDUE);
            }
            responseDTOs.add(dto);
        }
        return responseDTOs;
	}

    // Implementa exactamente obtenerPrestamoPorId(Long id) declarado en ILoanService; añade @Override y Javadoc completo en español; busca el préstamo mediante loanRepository.findById(id), almacena el resultado en Optional<Loan>, comprueba con isEmpty() si no existe y lanza ResourceNotFoundException con un mensaje claro en español; recupera la entidad mediante get(), conviértela con LoanMapper.toResponseDTO(loan) y, si su estado es ACTIVE y expectedReturnDate es anterior a LocalDate.now(), establece OVERDUE únicamente en el DTO sin modificar ni guardar la entidad; devuelve el DTO y utiliza Optional de java.util, sin lambdas, Streams ni referencias a métodos. -> generado por IA
	/**
     * Obtiene un préstamo por su ID.
     *
     * @param id El ID del préstamo a buscar.
     * @return Un objeto LoanResponseDTO con los datos del préstamo.
     * @throws ResourceNotFoundException si el préstamo no existe.
     */
    @Override
	public LoanResponseDTO obtenerPrestamoPorId(Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isEmpty()) {
            throw new ResourceNotFoundException("Préstamo con ID " + id + " no encontrado.");
        }
        Loan loan = optionalLoan.get();
        LoanResponseDTO dto = LoanMapper.toResponseDTO(loan);
        if (loan.getStatus() == LoanStatus.ACTIVE && loan.getExpectedReturnDate().isBefore(LocalDate.now())) {
            dto.setStatus(LoanStatus.OVERDUE);
        }
        return dto;
    }

    // Implementa exactamente crearPrestamo(LoanRequestDTO loanRequestDTO) declarado en ILoanService; añade @Override y Javadoc completo en español; busca el libro mediante bookRepository.findById(loanRequestDTO.getBookId()), almacena el resultado en Optional<Book>, comprueba con isEmpty() si no existe y lanza ResourceNotFoundException; recupera el libro con get() y comprueba que availableStock sea mayor que cero, si no lo es lanza ResourceConflictException; crea la entidad Loan mediante LoanMapper.toEntity(loanRequestDTO), asigna el libro y establece el estado LoanStatus.ACTIVE; reduce availableStock en una unidad; guarda el libro mediante bookRepository.save(book) y el préstamo mediante loanRepository.save(loan), cada uno exactamente una vez; convierte el préstamo guardado mediante LoanMapper.toResponseDTO y devuelve el DTO; utiliza Optional de java.util y no emplees lambdas, Streams, referencias a métodos ni operaciones en cascada. -> generado por IA
	
    /**
     * Crea un nuevo préstamo.
     *
     * @param loanRequestDTO Los datos del préstamo a crear.
     * @return Un objeto LoanResponseDTO con los datos del préstamo creado.
     * @throws ResourceNotFoundException si el libro no existe.
     * @throws ResourceConflictException si no hay stock disponible para el libro.
     */
    @Override
	public LoanResponseDTO crearPrestamo(LoanRequestDTO loanRequestDTO) {
        Optional<Book> optionalBook = bookRepository.findById(loanRequestDTO.getBookId());
        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("Libro con ID " + loanRequestDTO.getBookId() + " no encontrado.");
        }
        Book book = optionalBook.get();
        if (book.getAvailableStock() <= 0) {
            throw new ResourceConflictException("No hay stock disponible para el libro con ID " + book.getId() + ".");
        }

        Loan loan = LoanMapper.toEntity(loanRequestDTO);
        loan.setBook(book);
        loan.setStatus(LoanStatus.ACTIVE);

        book.setAvailableStock(book.getAvailableStock() - 1);
        bookRepository.save(book);
        Loan savedLoan = loanRepository.save(loan);

        return LoanMapper.toResponseDTO(savedLoan);
    }

    // Implementa exactamente devolverLibro(Long id) declarado en ILoanService; añade @Override y Javadoc completo en español; busca el préstamo mediante loanRepository.findById(id), almacena el resultado en Optional<Loan>, comprueba con isEmpty() si no existe y lanza ResourceNotFoundException; recupera el préstamo mediante get() y comprueba que su estado sea ACTIVE, si no lo es lanza ResourceConflictException indicando que ya ha sido devuelto; comprueba también que no exista una devolución previa; establece actualReturnDate utilizando LocalDate.now() y cambia el estado a RETURNED; recupera el libro asociado al préstamo, incrementa availableStock en una unidad y guarda el libro mediante bookRepository.save(book) exactamente una vez; guarda el préstamo actualizado mediante loanRepository.save(loan) exactamente una vez; convierte el préstamo guardado mediante LoanMapper.toResponseDTO y devuelve el DTO; utiliza Optional de java.util y no emplees lambdas, Streams, referencias a métodos ni operaciones en cascada. -> generado por IA
    /**
     * Devuelve un libro prestado.
     *
     * @param id El ID del préstamo a devolver.
     * @return Un objeto LoanResponseDTO con los datos del préstamo devuelto.
     * @throws ResourceNotFoundException si el préstamo no existe.
     * @throws ResourceConflictException si el préstamo ya ha sido devuelto.
     */
    @Override
	public LoanResponseDTO devolverLibro(Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isEmpty()) {
            throw new ResourceNotFoundException("Préstamo con ID " + id + " no encontrado.");
        }
        Loan loan = optionalLoan.get();
        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new ResourceConflictException("El préstamo con ID " + id + " ya ha sido devuelto.");
        }

        loan.setActualReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Book book = loan.getBook();
        book.setAvailableStock(book.getAvailableStock() + 1);
        bookRepository.save(book);
        Loan savedLoan = loanRepository.save(loan);

        return LoanMapper.toResponseDTO(savedLoan);
    }

}