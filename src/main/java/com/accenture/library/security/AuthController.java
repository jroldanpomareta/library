package com.accenture.library.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.accenture.library.dto.request.LoginRequestDTO;
import com.accenture.library.dto.response.LoginResponseDTO;
import com.accenture.library.security.SecurityConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// Crea AuthController en el paquete com.accenture.library.security. Inyéctale AuthenticationManager y JwtService mediante constructor. Implementa POST /auth/login que reciba LoginRequestDTO mediante @Valid y @RequestBody, autentique las credenciales usando UsernamePasswordAuthenticationToken y AuthenticationManager.authenticate(), obtenga el usuario autenticado y su rol, genere un JWT mediante JwtService.generarToken() y devuelva ResponseEntity<LoginResponseDTO> con HTTP 200. Si las credenciales son incorrectas, deja que Spring Security gestione la excepción de autenticación. Añade @RestController, @RequestMapping("/auth") y documentación Swagger en español con @Tag, @Operation y @ApiResponses. Documenta la respuesta 200 y el error 401. Añade Javadocs en español a la clase, constructor y método. No utilices lambdas, Streams ni referencias a métodos. -> generado por IA
/**
 * Controlador para la autenticación de usuarios.
 * Proporciona un endpoint para iniciar sesión y obtener un token JWT.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Tag(name = "Autenticación", description = "Endpoints para la autenticación de usuarios")
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Gestor de autenticación para validar credenciales.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * Servicio para generar tokens JWT.
     */
    private final JwtService jwtService;

    /**
     * Constructor de AuthController.
     *
     * @param authenticationManager el gestor de autenticación para validar
     *                              credenciales
     * @param jwtService            el servicio para generar tokens JWT
     */
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Implementa el endpoint POST /auth/login.
     * Recibe LoginRequestDTO mediante @Valid y @RequestBody.
     * Autentica las credenciales utilizando AuthenticationManager.authenticate() y
     * UsernamePasswordAuthenticationToken.
     * Obtiene el nombre de usuario y el rol desde Authentication.getAuthorities(),
     * elimina el prefijo ROLE_ y genera el JWT mediante JwtService.generarToken().
     * Devuelve ResponseEntity<LoginResponseDTO> con HTTP 200.
     * Añade @Operation y @ApiResponses documentando 200 y 401 con descripciones en
     * español.
     * Añade Javadocs al constructor y al método.
     * No utilices lambdas, Streams ni referencias a métodos.
     *
     * @param loginRequestDTO los datos de inicio de sesión
     * @return ResponseEntity<LoginResponseDTO> con el token JWT
     */
    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario iniciar sesión en la aplicación")
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso")
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()));

        String username = authentication.getName();
        String role = null;

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String authorityName = authority.getAuthority();

            if (authorityName.startsWith(SecurityConstants.ROLE_PREFIX)) {
                role = authorityName.replace(SecurityConstants.ROLE_PREFIX, "");
                break;
            }
        }

        String token = jwtService.generarToken(username, role);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}
