package com.accenture.library.service.interfaces;

import java.util.List;

import com.accenture.library.dto.request.LoanRequestDTO;
import com.accenture.library.dto.response.LoanResponseDTO;

// Crea la interfaz pública ILoanService en el paquete com.accenture.library.service.interfaces con los métodos para listar todos los préstamos, obtener un préstamo por su identificador, crear un préstamo y devolver un libro mediante el identificador del préstamo; utiliza List<LoanResponseDTO> para listar, Long para los identificadores, LoanRequestDTO como entrada y LoanResponseDTO como respuesta; incluye Javadoc en español en la interfaz y en todos sus métodos, documenta parámetros y retornos, importa las clases necesarias y no utilices lambdas, referencias a métodos ni Streams. -> generado por IA
public interface ILoanService {
    /**
     * Lista todos los préstamos.
     *
     * @return Una lista de objetos LoanResponseDTO que representan los préstamos encontrados.
     */
    List<LoanResponseDTO> listarPrestamos();

    /**
     * Obtiene un préstamo por su ID.
     *
     * @param id El ID del préstamo a obtener.
     * @return El objeto LoanResponseDTO que representa el préstamo obtenido.
     */
    LoanResponseDTO obtenerPrestamoPorId(Long id);

    /**
     * Crea un nuevo préstamo.
     *
     * @param loanRequestDTO El objeto que contiene los datos del préstamo a crear.
     * @return El objeto LoanResponseDTO que representa el préstamo creado.
     */
    LoanResponseDTO crearPrestamo(LoanRequestDTO loanRequestDTO);

    /**
     * Devuelve un libro mediante su ID de préstamo.
     *
     * @param id El ID del préstamo del libro a devolver.
     * @return El objeto LoanResponseDTO que representa el préstamo con el libro devuelto.
     */
    //que devuelva LoanResponseDTO en lugar de void -> generado por IA
    LoanResponseDTO devolverLibro(Long id);
}