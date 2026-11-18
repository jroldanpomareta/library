package com.accenture.library.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.config.Customizer;
import java.util.List;

// Crea SecurityConfig como configuración de Spring Security. Define los siguientes beans: PasswordEncoder usando BCryptPasswordEncoder; UserDetailsService con usuarios en memoria admin/admin123 con rol ADMIN y user/user123 con rol USER, almacenando las contraseñas codificadas con BCrypt; AuthenticationManager obtenido mediante AuthenticationConfiguration; y SecurityFilterChain. Configura la seguridad sin sesiones (STATELESS), desactiva CSRF, permite sin autenticación POST /auth/login y las rutas de Swagger/OpenAPI (/swagger-ui/**, /v3/api-docs/** y /swagger-ui.html), exige rol ADMIN para cualquier petición DELETE y autenticación para el resto. Integra JwtAuthenticationFilter antes de UsernamePasswordAuthenticationFilter. Añade Javadocs en español a la clase y todos sus métodos. Utiliza inyección por constructor cuando sea necesaria. -> generado por IA
/**
 * Clase de configuración de seguridad.
 * 
 * Esta clase configura la seguridad de la aplicación utilizando Spring
 * Security.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // injecta las contraseñas de los usuarios desde application.properties ->
    // generado por IA
    @Value("${app.security.admin.password}")
    private String adminPassword;

    @Value("${app.security.user.password}")
    private String userPassword;

    // Inyecta desde application.properties los nombres de usuario y las contraseñas
    // de las cuentas ADMIN y USER mediante @Value, evitando credenciales
    // hardcodeadas -> generado por IA

    @Value("${app.security.admin.username}")
    private String adminUsername;

    @Value("${app.security.user.username}")
    private String userUsername;

    /**
     * Filtro de autenticación JWT.
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructor de SecurityConfig.
     *
     * @param jwtAuthenticationFilter La instancia de JwtAuthenticationFilter a
     *                                utilizar.
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Implementa los siguientes beans: PasswordEncoder con BCryptPasswordEncoder;
    // UserDetailsService con dos usuarios en memoria: admin/admin123 con rol ADMIN
    // y user/user123 con rol USER, codificando ambas contraseñas con
    // PasswordEncoder; AuthenticationManager obtenido mediante
    // AuthenticationConfiguration; y SecurityFilterChain. Configura CSRF
    // desactivado, sesiones STATELESS, POST /auth/login y Swagger/OpenAPI
    // públicos, cualquier DELETE protegido con hasRole("ADMIN") y el resto de
    // peticiones autenticadas. Añade JwtAuthenticationFilter antes de
    // UsernamePasswordAuthenticationFilter. Utiliza @Configuration y
    // @EnableWebSecurity. Añade Javadocs en español a todos los métodos y los
    // imports necesarios. -> generado por IA
    /**
     * Bean para el codificador de contraseñas.
     *
     * @return Un PasswordEncoder que utiliza BCrypt para codificar las
     *         contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Sustituye las credenciales hardcodeadas de los usuarios ADMIN y USER por
    // propiedades inyectadas desde application.properties mediante @Value,
    // incluyendo nombres de usuario y contraseñas. Utiliza estos valores en
    // User.builder(), manteniendo las autoridades ROLE_ADMIN, ROLE_USER y
    // FACTOR_PASSWORD, y codificando las contraseñas mediante PasswordEncoder. No
    // modifiques la lógica de autenticación ni los requisitos de seguridad del
    // proyecto. -> generado por IA
    /**
     * Bean para el servicio de detalles de usuario.
     *
     * @param passwordEncoder El codificador de contraseñas a utilizar.
     * @return Un UserDetailsService con dos usuarios en memoria: admin/admin123 con
     *         rol ADMIN
     *         y user/user123 con rol USER.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .authorities(SecurityConstants.ROLE_PREFIX + "ADMIN", "FACTOR_PASSWORD")
                .build();

        UserDetails user = User.builder()
                .username(userUsername)
                .password(passwordEncoder.encode(userPassword))
                .authorities(SecurityConstants.ROLE_PREFIX + "USER", "FACTOR_PASSWORD")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    /**
     * Bean para el administrador de autenticación.
     *
     * @param config La configuración de autenticación.
     * @return Un AuthenticationManager.
     * @throws Exception Si ocurre un error al obtener el AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

    /**
     * Bean para la cadena de filtros de seguridad.
     *
     * @param http La configuración de seguridad HTTP.
     * @return Una SecurityFilterChain que define la cadena de filtros de seguridad.
     * @throws Exception Si ocurre un error al configurar la cadena de filtros.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/v1/books/**",
                                "/api/v1/authors/**",
                                "/api/v1/categories/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/", "/index-secure.html").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configura CORS para permitir peticiones desde cualquier origen, con los
    // métodos HTTP y cabeceras requeridos para el desarrollo y las pruebas locales,
    // registrando la configuración para todas las rutas y utilizando los valores
    // predeterminados de Spring Security -> generado por IA
    /**
     * Configura las políticas de acceso CORS de la aplicación.
     *
     * @return Configuración CORS para las solicitudes HTTP.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
