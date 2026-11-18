package com.accenture.library.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.library.dto.BookSearchCriteriaDTO;
import com.accenture.library.dto.error.ErrorResponse;
import com.accenture.library.dto.request.BookRequestDTO;
import com.accenture.library.dto.response.BookPageResponseDTO;
//pon el import de BookResponseDTO ese no es -> generado por IA
import com.accenture.library.dto.response.BookResponseDTO;

import com.accenture.library.service.interfaces.IBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// Crear la clase BookController completa en el paquete com.accenture.library.controller como controlador REST mediante @RestController y @RequestMapping("/api/v1/books"). Inyectar IBookService mediante constructor y declarar la clase como final si no impide el funcionamiento de Spring. Implementar los siguientes cinco endpoints: 1) GET /api/v1/books para listar libros; 2) GET /api/v1/books/{id} para consultar un libro; 3) POST /api/v1/books para crear un libro; 4) PUT /api/v1/books/{id} para actualizar un libro; 5) DELETE /api/v1/books/{id} para eliminar un libro. Para el listado, utilizar @RequestParam con page int valor por defecto 0, size int valor por defecto 20, sort String valor por defecto "title,asc", title String, author String, isbn String, categoryId Long, authorId Long y available Boolean. Delegar el listado en bookService.listarLibros(page, size, sort, title, author, isbn, categoryId, authorId, available) y devolver ResponseEntity<Page<BookResponseDTO>>. Para consultar un libro, utilizar @PathVariable Long id, llamar a bookService.obtenerLibroPorId(id) y devolver ResponseEntity<BookResponseDTO> con HTTP 200. Para crear, utilizar @Valid y @RequestBody BookRequestDTO, llamar a bookService.crearLibro(bookRequestDTO) y devolver el DTO con HTTP 201 CREATED. Para actualizar, utilizar @PathVariable Long id y @Valid @RequestBody BookRequestDTO, llamar a bookService.actualizarLibro(id, bookRequestDTO) y devolver el DTO con HTTP 200 OK. Para eliminar, utilizar @PathVariable Long id, llamar a bookService.eliminarLibro(id) y devolver HTTP 204 NO_CONTENT sin body. Añadir @Tag de Swagger a la clase y @Operation y @ApiResponses a cada endpoint, documentando en español todos los códigos de respuesta exigidos: listado 200 y 400; consulta 200 y 404; creación 201, 400 y 409; actualización 200, 400, 404 y 409; eliminación 204, 404 y 409. Añadir todos los imports necesarios. Documentar con Javadoc en español la clase, el constructor y todos los métodos, incluyendo parámetros y retornos. No implementar lógica de negocio en el controlador. No utilizar lambdas, Streams, method references ni CascadeType.ALL o CascadeType.REMOVE. Utilizar exclusivamente las interfaces, DTOs y métodos de servicio existentes en el proyecto. -> generado por IA
// Configurar esta clase como controlador REST mediante @RestController, establecer la ruta base /api/v1/books con @RequestMapping y documentar el controlador con @Tag de Swagger, indicando que gestiona las operaciones de libros -> generado por IA
// Modifica este método para que devuelva ResponseEntity<BookPageResponseDTO> en lugar de ResponseEntity<Page<BookResponseDTO>>. Conserva todos los parámetros y la llamada actual al servicio. Convierte el Page<BookResponseDTO> recibido en un BookPageResponseDTO utilizando getContent(), getNumber(), getSize(), getTotalElements(), getTotalPages() e isLast(). Mantén el código limpio y añade Javadoc en español si es necesario. -> generado por IA
/**
 * Controlador REST para gestionar las operaciones relacionadas con los libros.
 * Proporciona endpoints para listar, consultar, crear, actualizar y eliminar
 * libros.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Tag(name = "Libros", description = "Gestiona las operaciones de libros")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

        /**
         * Servicio para gestionar las operaciones de libros.
         */
        private final IBookService bookService;

        /**
         * Constructor para inyectar el servicio de libros.
         * 
         * @param bookService El servicio de libros a inyectar.
         */
        public BookController(IBookService bookService) {
                this.bookService = bookService;
        }

        // Implementar el endpoint GET para listar libros mediante @GetMapping. Recibir
        // los parámetros page (int, valor por defecto 0), size (int, valor por defecto
        // 20), sort (String, valor por defecto "title,asc"), title (String), author
        // (String), isbn (String), categoryId (Long), authorId (Long) y available
        // (Boolean), todos mediante @RequestParam. Llamar a bookService.listarLibros
        // pasando los nueve parámetros en el orden definido por su interfaz y devolver
        // el resultado mediante ResponseEntity.ok(). El tipo de retorno debe ser
        // ResponseEntity<Page<BookResponseDTO>>. Añadir @Operation y @ApiResponses de
        // Swagger en español para documentar las respuestas HTTP 200 y 400. Añadir
        // Javadoc completo en español con @param para cada parámetro y @return ->
        // generado por IA
        /**
         * Endpoint para listar los libros disponibles con paginación y filtros.
         * 
         * @param page       Número de página (valor por defecto: 0).
         * @param size       Tamaño de página (valor por defecto: 20).
         * @param sort       Criterio de ordenación (valor por defecto: "title,asc").
         * @param title      Filtro por título del libro (opcional).
         * @param author     Filtro por autor del libro (opcional).
         * @param isbn       Filtro por ISBN del libro (opcional).
         * @param categoryId Filtro por ID de categoría del libro (opcional).
         * @param authorId   Filtro por ID de autor del libro (opcional).
         * @param available  Filtro por disponibilidad del libro (opcional).
         * @return ResponseEntity con la página de libros listados y el código HTTP
         *         correspondiente.
         */
        @Operation(summary = "Listar libros", description = "Lista los libros disponibles con paginación y filtros")
        // Completar la documentación Swagger de BookController añadiendo el esquema
        // ErrorResponse mediante @Content(schema = @Schema(implementation =
        // ErrorResponse.class)) en todas las respuestas HTTP 400, 404 y 409. Añadir
        // también la respuesta HTTP 403 en el endpoint DELETE, indicando que un usuario
        // con rol USER no tiene permiso para eliminar libros. Mantener exactamente los
        // códigos, descripciones y anotaciones que ya son correctos, sin modificar
        // ningún endpoint, método, firma, lógica, Javadoc ni comentario existente.
        // Importar ErrorResponse desde su paquete real. No modificar nada más. ->
        // generado por IA
        @ApiResponse(responseCode = "200", description = "Libros listados correctamente")
        @ApiResponse(responseCode = "400", description = "Parámetros de solicitud inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "401", description = "Conserva aquí la descripción actual", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @GetMapping
        public ResponseEntity<BookPageResponseDTO> listarLibros(
                        @ModelAttribute BookSearchCriteriaDTO criterios) {

                Page<BookResponseDTO> libros = bookService.listarLibros(criterios);

                BookPageResponseDTO respuesta = new BookPageResponseDTO(
                                libros.getContent(),
                                libros.getNumber(),
                                libros.getSize(),
                                libros.getTotalElements(),
                                libros.getTotalPages(),
                                libros.isLast());

                return ResponseEntity.ok(respuesta);
        }

        // Implementar el endpoint GET /{id} mediante @GetMapping("/{id}"). Recibir el
        // identificador Long id con @PathVariable, llamar a
        // bookService.obtenerLibroPorId(id) y devolver el resultado mediante
        // ResponseEntity.ok(). El tipo de retorno debe ser
        // ResponseEntity<BookResponseDTO>. Añadir @Operation y @ApiResponses de Swagger
        // en español para documentar las respuestas HTTP 200 (libro encontrado), 404
        // (libro no encontrado) y 401 (usuario no autenticado). Añadir Javadoc completo
        // en español con @param id y @return. No implementar lógica de negocio ni
        // modificar el método listarLibros -> generado por IA
        /**
         * Endpoint para obtener un libro por su ID.
         *
         * @param id El ID del libro a obtener.
         * @return ResponseEntity con el libro encontrado y el código HTTP
         *         correspondiente.
         */
        @Operation(summary = "Obtener libro por ID", description = "Obtiene un libro específico por su ID")
        // Completar la documentación Swagger de BookController añadiendo el esquema
        // ErrorResponse mediante @Content(schema = @Schema(implementation =
        // ErrorResponse.class)) en todas las respuestas HTTP 400, 404 y 409. Añadir
        // también la respuesta HTTP 403 en el endpoint DELETE, indicando que un usuario
        // con rol USER no tiene permiso para eliminar libros. Mantener exactamente los
        // códigos, descripciones y anotaciones que ya son correctos, sin modificar
        // ningún endpoint, método, firma, lógica, Javadoc ni comentario existente.
        // Importar ErrorResponse desde su paquete real. No modificar nada más. ->
        // generado por IA
        @ApiResponse(responseCode = "200", description = "Libro encontrado correctamente")
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @GetMapping("/{id}")
        public ResponseEntity<BookResponseDTO> obtenerLibroPorId(@PathVariable Long id) {
                BookResponseDTO libro = bookService.obtenerLibroPorId(id);
                return ResponseEntity.ok(libro);
        }

        // Implementar el endpoint POST mediante @PostMapping. Recibir un BookRequestDTO
        // en el cuerpo de la petición con @Valid y @RequestBody, llamar a
        // bookService.crearLibro(bookRequestDTO) y devolver el BookResponseDTO
        // resultante mediante ResponseEntity.status(HttpStatus.CREATED).body(...).
        // Añadir @Operation y @ApiResponses de Swagger en español para documentar las
        // respuestas HTTP 201 (libro creado), 400 (datos inválidos), 409 (conflicto por
        // ISBN duplicado) y 401 (usuario no autenticado). Añadir Javadoc completo en
        // español con @param y @return. No implementar lógica de negocio ni modificar
        // los endpoints existentes -> generado por IA
        /**
         * Endpoint para crear un nuevo libro.
         *
         * @param bookRequestDTO DTO con los datos del libro a crear.
         * @return ResponseEntity con el libro creado y el código HTTP correspondiente.
         */
        @Operation(summary = "Crear libro", description = "Crea un nuevo libro")
        // Completar la documentación Swagger de BookController añadiendo el esquema
        // ErrorResponse mediante @Content(schema = @Schema(implementation =
        // ErrorResponse.class)) en todas las respuestas HTTP 400, 404 y 409. Añadir
        // también la respuesta HTTP 403 en el endpoint DELETE, indicando que un usuario
        // con rol USER no tiene permiso para eliminar libros. Mantener exactamente los
        // códigos, descripciones y anotaciones que ya son correctos, sin modificar
        // ningún endpoint, método, firma, lógica, Javadoc ni comentario existente.
        // Importar ErrorResponse desde su paquete real. No modificar nada más. ->
        // generado por IA
        @ApiResponse(responseCode = "201", description = "Libro creado correctamente")
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "409", description = "Conflicto por ISBN duplicado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @PostMapping
        public ResponseEntity<BookResponseDTO> crearLibro(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
                BookResponseDTO libro = bookService.crearLibro(bookRequestDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(libro);
        }

        // Implementar el endpoint PUT /{id} mediante @PutMapping("/{id}"). Recibir Long
        // id con @PathVariable y BookRequestDTO mediante @Valid y @RequestBody. Llamar
        // a bookService.actualizarLibro(id, bookRequestDTO) y devolver el
        // BookResponseDTO mediante ResponseEntity.ok(). Añadir @Operation y
        // @ApiResponses de Swagger en español para documentar HTTP 200 (actualizado),
        // 400 (datos inválidos), 404 (libro no encontrado), 409 (conflicto por ISBN
        // duplicado) y 401 (usuario no autenticado). Añadir Javadoc completo en español
        // con @param para id y bookRequestDTO y @return. No modificar los endpoints
        // existentes -> generado por IA
        /**
         * Endpoint para actualizar un libro existente.
         *
         * @param id             El ID del libro a actualizar.
         * @param bookRequestDTO DTO con los datos actualizados del libro.
         * @return ResponseEntity con el libro actualizado y el código HTTP
         *         correspondiente.
         */
        // Añadir @Operation y @ApiResponses de Swagger en español para documentar las
        // respuestas HTTP 200 (libro actualizado), 400 (datos inválidos), 404 (libro no
        // encontrado), 409 (conflicto por ISBN duplicado) y 401 (usuario no
        // autenticado). No modificar la lógica ni la firma del método -> generado por
        // IA
        @Operation(summary = "Actualizar libro", description = "Actualiza un libro existente")
        // Completar la documentación Swagger de BookController añadiendo el esquema
        // ErrorResponse mediante @Content(schema = @Schema(implementation =
        // ErrorResponse.class)) en todas las respuestas HTTP 400, 404 y 409. Añadir
        // también la respuesta HTTP 403 en el endpoint DELETE, indicando que un usuario
        // con rol USER no tiene permiso para eliminar libros. Mantener exactamente los
        // códigos, descripciones y anotaciones que ya son correctos, sin modificar
        // ningún endpoint, método, firma, lógica, Javadoc ni comentario existente.
        // Importar ErrorResponse desde su paquete real. No modificar nada más. ->
        // generado por IA
        @ApiResponse(responseCode = "200", description = "Libro actualizado correctamente")
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "409", description = "Conflicto por ISBN duplicado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @PutMapping("/{id}")
        public ResponseEntity<BookResponseDTO> actualizarLibro(@PathVariable Long id,
                        @Valid @RequestBody BookRequestDTO bookRequestDTO) {
                BookResponseDTO libro = bookService.actualizarLibro(id, bookRequestDTO);
                return ResponseEntity.ok(libro);
        }

        // Implementar el endpoint DELETE /{id} mediante @DeleteMapping("/{id}").
        // Recibir Long id con @PathVariable, llamar a bookService.eliminarLibro(id) y
        // devolver ResponseEntity.noContent().build() con HTTP 204 y sin cuerpo. Añadir
        // @Operation y @ApiResponses de Swagger en español para documentar HTTP 204
        // (libro eliminado), 404 (libro no encontrado), 409 (libro asociado a
        // préstamos) y 401 (usuario no autenticado). Añadir Javadoc completo en español
        // con @param id y @return. No implementar lógica de negocio ni modificar los
        // endpoints anteriores -> generado por IA
        /**
         * Endpoint para eliminar un libro por su ID.
         *
         * @param id El ID del libro a eliminar.
         * @return ResponseEntity con el código HTTP correspondiente.
         */
        @Operation(summary = "Eliminar libro", description = "Elimina un libro existente")
        // Completar la documentación Swagger de BookController añadiendo el esquema
        // ErrorResponse mediante @Content(schema = @Schema(implementation =
        // ErrorResponse.class)) en todas las respuestas HTTP 400, 404 y 409. Añadir
        // también la respuesta HTTP 403 en el endpoint DELETE, indicando que un usuario
        // con rol USER no tiene permiso para eliminar libros. Mantener exactamente los
        // códigos, descripciones y anotaciones que ya son correctos, sin modificar
        // ningún endpoint, método, firma, lógica, Javadoc ni comentario existente.
        // Importar ErrorResponse desde su paquete real. No modificar nada más. ->
        // generado por IA
        @ApiResponse(responseCode = "204", description = "Libro eliminado correctamente")
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "409", description = "No se puede eliminar el libro porque tiene préstamos asociados", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @ApiResponse(responseCode = "403", description = "Acceso denegado: el usuario no tiene permiso para eliminar libros", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
                bookService.eliminarLibro(id);
                return ResponseEntity.noContent().build();
        }

}
