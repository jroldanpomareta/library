package com.accenture.library.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

// Crea un filtro JwtAuthenticationFilter que extienda OncePerRequestFilter. Inyecta JwtService mediante constructor. En doFilterInternal, obtiene la cabecera Authorization, comprueba que comienza por Bearer, extrae el token y valida su autenticidad. Si es válido y no existe autenticación previa, extrae username y role, crea una UsernamePasswordAuthenticationToken con una SimpleGrantedAuthority usando el prefijo ROLE_, establece los detalles mediante WebAuthenticationDetailsSource y guarda la autenticación en SecurityContextHolder. Si el token es inválido, continúa la cadena sin autenticar. Añade Javadocs en español a la clase, constructor y método sobrescrito. -> generado por IA
/**
 * Filtro para la autenticación con JWT.
 * 
 * Este filtro intercepta las solicitudes HTTP y valida el token JWT presente en la cabecera Authorization. Si el token es válido, se establece la autenticación en el contexto de seguridad.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Servicio para la gestión de tokens JWT.
     */
    private final JwtService jwtService;

    /**
     * Constructor de JwtAuthenticationFilter.
     *
     * @param jwtService La instancia de JwtService a utilizar.
     */
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Método que se ejecuta en cada solicitud HTTP.
     *
     * @param request  La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @param filterChain La cadena de filtros.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith(SecurityConstants.BEARER_PREFIX)) {
            String token = authHeader.substring(7);
            if (jwtService.validarToken(token)) {
                String username = jwtService.extraerUsername(token);
                String role = jwtService.extraerRol(token);
                if (username != null && role != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, java.util.Collections.singletonList(new SimpleGrantedAuthority(SecurityConstants.ROLE_PREFIX + role)));
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
