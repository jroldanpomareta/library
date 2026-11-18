package com.accenture.library.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.context.annotation.Import;
import com.accenture.library.security.JwtService;
import com.accenture.library.security.SecurityConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.accenture.library.dto.request.BookRequestDTO;
import com.accenture.library.dto.response.BookResponseDTO;
import com.accenture.library.exception.ResourceNotFoundException;
import com.accenture.library.service.interfaces.IBookService;

// Desarrolla la clase de pruebas unitarias BookControllerTest utilizando JUnit 5, Spring MockMvc y Mockito. Configura las pruebas con @WebMvcTest(BookController.class), inyecta MockMvc y simula la dependencia IBookService mediante @MockitoBean. Mantén activa la configuración de seguridad y utiliza @WithMockUser con el rol USER para ejecutar las peticiones autenticadas. Implementa los tres casos de prueba definidos en esta clase, siguiendo Given, When y Then, con nombres descriptivos y Javadoc en español. -> generado por IA
/**
 * Clase de pruebas unitarias para el controlador BookController.
 * Utiliza JUnit 5, Mockito y Spring MockMvc para comprobar las respuestas HTTP.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "user", roles = { "USER" })
class BookControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private IBookService bookService;

        @MockitoBean
        private JwtService jwtService;

        // Desarrolla un test que compruebe que consultar un libro inexistente devuelve
        // HTTP 404. Simula con Mockito que el servicio lanza ResourceNotFoundException
        // y realiza la petición GET a /api/v1/books/999. Comprueba el estado HTTP 404
        // utilizando MockMvc. Incluye los comentarios Given, When y Then. -> generado
        // por IA

        /**
         * Comprueba que consultar un libro inexistente devuelve HTTP 404.
         *
         * @throws Exception si se produce un error durante la petición HTTP.
         */
        @Test
        void obtenerLibroPorId_shouldReturnNotFound_whenBookDoesNotExist() throws Exception {

                // Given
                when(bookService.obtenerLibroPorId(999L))
                                .thenThrow(new ResourceNotFoundException("Libro no encontrado"));

                // When
                mockMvc.perform(get("/api/v1/books/999"))

                                // Then
                                .andExpect(status().isNotFound());
        }

        // Desarrolla un test que compruebe que crear un libro con datos inválidos
        // devuelve HTTP 400. Envía un JSON con el título vacío y el resto de campos
        // obligatorios válidos, incluyendo availableStock menor o igual que totalStock.
        // Realiza una petición POST a /api/v1/books con Content-Type application/json.
        // Comprueba el estado HTTP 400 y que la respuesta contiene el campo message.
        // Incluye los comentarios Given, When y Then. -> generado por IA

        /**
         * Comprueba que crear un libro con datos inválidos devuelve HTTP 400.
         *
         * @throws Exception si se produce un error durante la petición HTTP.
         */
        @Test
        void crearLibro_shouldReturnBadRequest_whenRequestIsInvalid() throws Exception {

                // Given
                String json = """
                                {
                                    "title": "",
                                    "isbn": "9780132350884",
                                    "availableStock": 5,
                                    "totalStock": 10
                                }
                                """;

                // When
                mockMvc.perform(post("/api/v1/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))

                                // Then
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.message").exists());
        }

        // Desarrolla un test que compruebe que crear un libro con datos válidos
        // devuelve HTTP 201. Simula con Mockito el método crearLibro del servicio para
        // que devuelva un BookResponseDTO. Envía un JSON válido con título, ISBN,
        // availableStock y totalStock. Realiza una petición POST a /api/v1/books con
        // Content-Type application/json y comprueba el estado HTTP 201 utilizando
        // MockMvc. Incluye los comentarios Given, When y Then. -> generado por IA

        /**
         * Comprueba que crear un libro válido devuelve HTTP 201.
         *
         * @throws Exception si se produce un error durante la petición HTTP.
         */
        @Test
        void crearLibro_shouldReturnCreated_whenRequestIsValid() throws Exception {

                // Given
                BookResponseDTO responseDTO = org.mockito.Mockito.mock(BookResponseDTO.class);

                when(bookService.crearLibro(any(BookRequestDTO.class)))
                                .thenReturn(responseDTO);

                String json = """
                                {
                                    "title": "Clean Code",
                                    "isbn": "9780132350884",
                                    "availableStock": 5,
                                    "totalStock": 10
                                }
                                """;

                // When
                mockMvc.perform(post("/api/v1/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))

                                // Then
                                .andExpect(status().isCreated());
        }
}