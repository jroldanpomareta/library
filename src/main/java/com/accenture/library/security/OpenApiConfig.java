package com.accenture.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

// Crea la clase OpenApiConfig en el paquete com.accenture.library.config.
// Anota la clase con @Configuration.
// Añade el Javadoc de clase indicando que configura la documentación OpenAPI
// de la API REST de gestión de biblioteca.
// Incluye @author Javier Roldán Pomareta y @version 1.0.
// -> generado por IA
/**
 * Configuración de la documentación OpenAPI de la API de biblioteca.
 *
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {

    // Crea un método público llamado customOpenAPI().
    // Anótalo con @Bean y establece que devuelve un objeto OpenAPI.
    // Añade Javadoc explicando que configura la información general y la seguridad
    // de OpenAPI, e indica que devuelve la configuración personalizada.
    // -> generado por IA
    /**
     * Configura la información general y la seguridad de OpenAPI.
     *
     * @return Configuración personalizada de OpenAPI.
     */
    @Bean
    public OpenAPI customOpenAPI() {

        Contact contact = new Contact();
        contact.setName("Soporte API Biblioteca");
        contact.setEmail("soporte@library-api.local");

        Info info = new Info();
        info.setTitle("API REST - Gestión de Biblioteca");
        info.setDescription("API para gestionar libros, autores, categorías y préstamos.");
        info.setVersion("1.0.0");
        info.setContact(contact);

        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Servidor local de desarrollo");

        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.HTTP);
        securityScheme.setScheme("bearer");
        securityScheme.setBearerFormat("JWT");

        Components components = new Components();
        components.addSecuritySchemes("bearerAuth", securityScheme);

        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.addServersItem(server);
        openAPI.setComponents(components);

        return openAPI;
    }
}