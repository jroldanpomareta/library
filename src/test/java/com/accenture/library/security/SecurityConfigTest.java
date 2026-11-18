
package com.accenture.library.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.accenture.library.controller.BookController;
import com.accenture.library.service.interfaces.IBookService;

// Desarrolla la clase de pruebas de seguridad SecurityConfigTest utilizando JUnit 5, Spring MockMvc y Mockito. Configura @WebMvcTest(BookController.class) e importa la configuración de seguridad real mediante @Import(SecurityConfig.class). Inyecta MockMvc y simula las dependencias IBookService y JwtService mediante @MockitoBean. No utilices MySQL ni desactives la seguridad. Implementa una prueba que compruebe que un usuario autenticado con rol USER recibe HTTP 403 al intentar eliminar un libro. Incluye Javadocs en español y comentarios Given, When y Then. -> generado por IA

/**
 * Clase de pruebas de seguridad para comprobar las restricciones de acceso.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IBookService bookService;

    @MockitoBean
    private JwtService jwtService;

    // Desarrolla un test que compruebe que un usuario autenticado con rol USER
    // recibe HTTP 403 al intentar eliminar un libro. Utiliza @WithMockUser(username
    // = "user", roles = {"USER"}) y realiza una petición DELETE a /api/v1/books/1
    // mediante MockMvc. Comprueba que la respuesta es HTTP 403. Incluye los
    // comentarios Given, When y Then. -> generado por IA

    /**
     * Comprueba que un usuario con rol USER no puede eliminar libros.
     *
     * @throws Exception si se produce un error durante la petición HTTP.
     */
    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    void eliminarLibro_shouldReturnForbidden_whenUserHasRoleUser() throws Exception {

        // Given
        // Usuario autenticado con rol USER.

        // When
        mockMvc.perform(delete("/api/v1/books/1"))

                // Then
                .andExpect(status().isForbidden());
    }
}
