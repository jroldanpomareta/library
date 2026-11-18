
// Crea la clase pública BookServiceImpl en el paquete com.accenture.library.service.impl que implemente la interfaz IBookService; añade las anotaciones @Service y @Transactional; declara tres atributos privados y finales de tipo IBookRepository, ICategoryRepository e IAuthorRepository; crea un constructor público que reciba esas tres dependencias y las asigne mediante this; añade los imports necesarios y Javadoc en español para la clase y el constructor; deja únicamente la estructura de la clase, sin implementar todavía los métodos de la interfaz. -> generado por IA
package com.accenture.library.service.impl;

import com.accenture.library.repository.interfaces.IAuthorRepository;
import com.accenture.library.repository.interfaces.IBookRepository;
import com.accenture.library.repository.interfaces.ICategoryRepository;
import com.accenture.library.service.interfaces.IBookService;
import com.accenture.library.dto.BookSearchCriteriaDTO;
import com.accenture.library.dto.request.BookRequestDTO;
import com.accenture.library.dto.response.BookResponseDTO;
import com.accenture.library.exception.ResourceConflictException;
import com.accenture.library.exception.ResourceNotFoundException;
import com.accenture.library.mapper.BookMapper;
import com.accenture.library.model.Author;
import com.accenture.library.model.Book;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import com.accenture.library.model.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementación del servicio de libros.
 * Proporciona métodos para gestionar libros en la biblioteca.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Service
@Transactional
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;
    private final ICategoryRepository categoryRepository;
    private final IAuthorRepository authorRepository;

    /**
     * Constructor de la clase BookServiceImpl.
     * 
     * @param bookRepository     Repositorio de libros.
     * @param categoryRepository Repositorio de categorías.
     * @param authorRepository   Repositorio de autores.
     */
    public BookServiceImpl(IBookRepository bookRepository, ICategoryRepository categoryRepository,
            IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    // Declara el método público listarLibros con retorno Page<BookResponseDTO> y
    // los parámetros int page, int size, String sort, String title, String author,
    // String isbn, Long categoryId, Long authorId y Boolean available, exactamente
    // en ese orden; añade @Override y Javadoc completo en español que describa su
    // finalidad, todos sus parámetros y el valor que devuelve; no implementes
    // todavía el cuerpo del método. -> generado por IA

    /**
     * Lista los libros según los criterios de búsqueda y paginación.
     *
     * @param page       Número de página (comienza en 0).
     * @param size       Tamaño de la página.
     * @param sort       Campo y dirección de ordenación (ej. "title,asc").
     * @param title      Título del libro a buscar.
     * @param author     Autor del libro a buscar.
     * @param isbn       ISBN del libro a buscar.
     * @param categoryId ID de la categoría del libro a buscar.
     * @param authorId   ID del autor del libro a buscar.
     * @param available  Disponibilidad del libro a buscar.
     * @return Página con los libros que cumplen los criterios de búsqueda.
     */
    @Override
    public Page<BookResponseDTO> listarLibros(BookSearchCriteriaDTO criterios) {
        int page = criterios.getPage();
        int size = criterios.getSize();
        String sort = criterios.getSort();
        String title = criterios.getTitle();
        String author = criterios.getAuthor();
        String isbn = criterios.getIsbn();
        Long categoryId = criterios.getCategoryId();
        Long authorId = criterios.getAuthorId();
        Boolean available = criterios.getAvailable();

        // saca la validación de paginación a un método privado llamado
        // validarPaginacion(int page, int size) que devuelva el tamaño de página
        // validado; llama a ese método al inicio de listarLibros y asigna el resultado
        // a size. -> generado por IA
        size = validarPaginacion(page, size);

        // Extrae toda la validación y construcción del objeto Sort del método
        // listarLibros a un método privado llamado validarOrdenacion(String sort) que
        // devuelva Sort; conserva el valor predeterminado "title,asc", valida el
        // formato, los campos permitidos (title, isbn, publishedYear, availableStock y
        // totalStock) y las direcciones asc y desc; conserva exactamente los mensajes y
        // excepciones ResponseStatusException con HttpStatus.BAD_REQUEST; añade siempre
        // id como segundo criterio de ordenación ascendente; sustituye el bloque
        // original por una llamada al nuevo método; añade Javadoc en español y no
        // utilices lambdas, referencias a métodos ni Streams. -> generado por IA
        Sort sorting = validarOrdenacion(sort);

        // Ejecuta la consulta mediante bookRepository.buscarLibros pasando los
        // parámetros title, author, isbn, categoryId, authorId, available y pageable,
        // en ese orden; almacena el resultado en un Page<Book>; crea una lista
        // ArrayList<BookResponseDTO>; recorre los libros obtenidos mediante un bucle
        // for clásico y convierte cada uno utilizando BookMapper.toResponseDTO,
        // añadiéndolo a la lista; devuelve un PageImpl<BookResponseDTO> utilizando la
        // lista, el pageable y booksPage.getTotalElements() para conservar los
        // metadatos de paginación; añade todos los imports necesarios y no utilices
        // lambdas, Streams ni referencias a métodos. -> generado por IA
        Pageable pageable = PageRequest.of(page, size, sorting);

        Page<Book> booksPage = bookRepository.buscarLibros(
                title,
                author,
                isbn,
                categoryId,
                authorId,
                available,
                pageable);

        // Extrae la conversión de Page<Book> a Page<BookResponseDTO> a un método
        // privado llamado convertirPaginaLibros(Page<Book> booksPage, Pageable
        // pageable); utiliza ArrayList y un bucle for tradicional para convertir cada
        // libro mediante BookMapper.toResponseDTO; conserva el total de elementos y el
        // Pageable original al construir PageImpl; añade Javadoc en español y no
        // utilices lambdas, referencias a métodos ni Streams. -> generado por IA
        return convertirPaginaLibros(booksPage, pageable);
    }

    // Implementa exactamente el método eliminarLibro(Long id) declarado en
    // IBookService; añade @Override y Javadoc completo en español; busca el libro
    // mediante bookRepository.findById(id), si no existe lanza
    // ResourceNotFoundException con un mensaje claro en español, comprueba si el
    // libro tiene préstamos asociados mediante su colección loans y, si no está
    // vacía, lanza ResourceConflictException indicando que no se puede eliminar un
    // libro con préstamos; si no tiene préstamos, elimínalo mediante
    // bookRepository.delete(book); añade los imports necesarios y no utilices
    // lambdas, Streams, referencias a métodos ni operaciones en cascada. ->
    // generado por IA
    // no uses lambdas, Streams, referencias a métodos ni operaciones en cascada
    // Regenera completamente eliminarLibro(Long id) utilizando Optional<Book> para
    // almacenar bookRepository.findById(id); comprueba con isEmpty() si el libro no
    // existe y lanza ResourceNotFoundException; obtiene el libro con get();
    // comprueba si book.getLoans() no está vacía y lanza ResourceConflictException
    // si tiene préstamos; finalmente ejecuta bookRepository.delete(book); añade
    // Javadoc completo en español y @Override; no utilices orElseThrow, lambdas,
    // Streams, referencias a métodos ni operaciones en cascada. -> generado por IA
    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     * @throws ResourceNotFoundException si el libro no existe.
     * @throws ResourceConflictException si el libro tiene préstamos asociados y no
     *                                   puede ser eliminado.
     */
    @Override
    public void eliminarLibro(Long id) {// no uses lambdas, Streams, referencias a métodos ni operaciones en cascada
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el libro con ID: " + id);
        }

        Book book = optionalBook.get();

        if (!book.getLoans().isEmpty()) {
            throw new ResourceConflictException(
                    "No se puede eliminar el libro con ID: " + id + " porque tiene préstamos asociados.");
        }

        bookRepository.delete(book);
    }

    // Implementa desde cero el método público actualizarLibro(Long id,
    // BookRequestDTO bookRequestDTO) que devuelve BookResponseDTO y sobrescribe
    // exactamente el método declarado en IBookService; añade @Override y Javadoc
    // completo en español con descripción, parámetros, retorno y excepciones; sigue
    // estrictamente este orden: 1) busca el libro con bookRepository.findById(id),
    // guarda el resultado en Optional<Book>, comprueba con isEmpty() si no existe y
    // lanza ResourceNotFoundException con un mensaje claro; 2) recupera el libro
    // mediante get(); 3) actualiza title, isbn, publishedYear, availableStock y
    // totalStock utilizando los valores del DTO; 4) si categoryId no es null, busca
    // la categoría con categoryRepository.findById, comprueba con isEmpty() si no
    // existe y lanza ResourceNotFoundException, y después asigna la categoría al
    // libro; si categoryId es null conserva la categoría actual; 5) si authorIds no
    // es null, crea una nueva ArrayList<Author>, recorre todos los identificadores
    // con un bucle for clásico, busca cada autor con authorRepository.findById,
    // comprueba con isEmpty() si no existe y lanza ResourceNotFoundException,
    // recupera cada autor con get(), comprueba mediante otro bucle for clásico si
    // su ID ya está en la nueva lista y solo lo añade cuando no exista, y
    // finalmente asigna la nueva lista con book.setAuthors(authors); si authorIds
    // es null conserva los autores actuales y si es una lista vacía elimina todas
    // las relaciones de autores; 6) fuera de todos los bloques condicionales,
    // guarda el libro exactamente una vez mediante bookRepository.save(book),
    // almacena el resultado en Book savedBook y devuelve exactamente una vez
    // BookMapper.toResponseDTO(savedBook); no dupliques el guardado ni el retorno;
    // utiliza Optional de java.util, añade los imports necesarios y no utilices
    // lambdas, Streams, referencias a métodos, métodos funcionales de colecciones
    // ni operaciones en cascada. -> generado por IA
    /**
     * Actualiza un libro existente con los datos proporcionados.
     *
     * @param id             ID del libro a actualizar.
     * @param bookRequestDTO DTO con los datos del libro a actualizar.
     * @return DTO con los datos del libro actualizado.
     * @throws ResourceNotFoundException si el libro no existe.
     * @throws ResourceConflictException si hay conflictos en los datos del libro.
     * @throws IllegalArgumentException  si los parámetros son inválidos.
     * @throws IllegalStateException     si el estado del sistema no permite la
     *                                   operación.
     * 
     */
    @Override
    public BookResponseDTO actualizarLibro(Long id, BookRequestDTO bookRequestDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el libro con ID: " + id);
        }
        Book book = optionalBook.get();
        book.setTitle(bookRequestDTO.getTitle());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPublishedYear(bookRequestDTO.getPublishedYear());
        book.setAvailableStock(bookRequestDTO.getAvailableStock());
        book.setTotalStock(bookRequestDTO.getTotalStock());
        if (bookRequestDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(bookRequestDTO.getCategoryId());
            if (optionalCategory.isEmpty()) {
                throw new IllegalArgumentException("No existe la categoría con ID: " + bookRequestDTO.getCategoryId());
            }
            book.setCategory(optionalCategory.get());
        }
        // saca la lógica de asignación de autores a un método privado llamado
        // obtenerAutores(List<Long> authorIds) que devuelva List<Author>; recorre los
        // identificadores con un bucle for clásico, busca cada autor con
        // authorRepository.findById, comprueba con isEmpty() si no existe y lanza
        // ResourceNotFoundException, recupera cada autor con get(), comprueba mediante
        // otro bucle for clásico si su ID ya está en la nueva lista y solo lo añade
        // cuando no exista; llama a ese método desde actualizarLibro y asigna el
        // resultado a book.setAuthors(); si authorIds es null conserva los autores
        // actuales y si es una lista vacía elimina todas las relaciones de autores. ->
        // generado por IA
        if (bookRequestDTO.getAuthorIds() != null) {
            book.setAuthors(obtenerAutores(bookRequestDTO.getAuthorIds()));
        }

        Book savedBook = bookRepository.save(book);
        return BookMapper.toResponseDTO(savedBook);
    }

    // Implementa exactamente el método crearLibro(BookRequestDTO bookRequestDTO)
    // declarado en IBookService; añade @Override y Javadoc completo en español;
    // sigue este orden: 1) convierte el DTO a entidad mediante
    // BookMapper.toEntity(bookRequestDTO); 2) si categoryId no es null, busca la
    // categoría mediante categoryRepository.findById, comprueba con isEmpty() si no
    // existe y lanza ResourceNotFoundException con un mensaje claro, después asigna
    // la categoría al libro; 3) si authorIds no es null, crea una nueva
    // ArrayList<Author>, recorre los identificadores mediante un bucle for clásico,
    // busca cada autor mediante authorRepository.findById, comprueba con isEmpty()
    // si no existe y lanza ResourceNotFoundException, recupera cada autor con
    // get(), evita duplicados comparando sus IDs mediante otro bucle for clásico y
    // añade únicamente los autores que no estén repetidos; asigna la lista mediante
    // book.setAuthors(authors), permitiendo que una lista vacía deje el libro sin
    // autores; 4) guarda el libro exactamente una vez mediante
    // bookRepository.save(book), almacena el resultado en Book savedBook y devuelve
    // BookMapper.toResponseDTO(savedBook); utiliza Optional de java.util, añade los
    // imports necesarios y no emplees lambdas, Streams, referencias a métodos,
    // métodos funcionales de colecciones ni operaciones en cascada. -> generado por
    // IA
    /**
     * Crea un nuevo libro con los datos proporcionados.
     *
     * @param bookRequestDTO DTO con los datos del libro a crear.
     * @return DTO con los datos del libro creado.
     * @throws ResourceNotFoundException si la categoría o algún autor no existen.
     * @throws ResourceConflictException si hay conflictos en los datos del libro.
     * @throws IllegalArgumentException  si los parámetros son inválidos.
     * @throws IllegalStateException     si el estado del sistema no permite la
     *                                   operación.
     */
    @Override
    public BookResponseDTO crearLibro(BookRequestDTO bookRequestDTO) {
        Book book = BookMapper.toEntity(bookRequestDTO);
        
        validarLibroExistente(bookRequestDTO.getTitle(), bookRequestDTO.getIsbn());

        if (bookRequestDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(bookRequestDTO.getCategoryId());
            if (optionalCategory.isEmpty()) {
                throw new IllegalArgumentException("No existe la categoría con ID: " + bookRequestDTO.getCategoryId());
            }
            book.setCategory(optionalCategory.get());
        }
        // saca la lógica de asignación de autores a un método privado llamado
        // obtenerAutores(List<Long> authorIds) que devuelva List<Author>; recorre los
        // identificadores con un bucle for clásico, busca cada autor con
        // authorRepository.findById, comprueba con isEmpty() si no existe y lanza
        // ResourceNotFoundException, recupera cada autor con get(), comprueba mediante
        // otro bucle for clásico si su ID ya está en la nueva lista y solo lo añade
        // cuando no exista; llama a ese método desde crearLibro y asigna el resultado a
        // book.setAuthors(); si authorIds es null conserva los autores actuales y si es
        // una lista vacía elimina todas las relaciones de autores. -> generado por IA
        if (bookRequestDTO.getAuthorIds() != null) {
            book.setAuthors(obtenerAutores(bookRequestDTO.getAuthorIds()));
        }

        Book savedBook = bookRepository.save(book);
        return BookMapper.toResponseDTO(savedBook);
    }

    // Implementa exactamente el método obtenerLibroPorId(Long id) declarado en
    // IBookService; añade @Override y Javadoc completo en español con descripción,
    // parámetro, retorno y excepción; busca el libro mediante
    // bookRepository.findById(id), almacena el resultado en Optional<Book>,
    // comprueba mediante isEmpty() si no existe y lanza ResourceNotFoundException
    // con un mensaje claro en español; si existe, recupera la entidad mediante
    // get() y devuelve su DTO utilizando BookMapper.toResponseDTO(book); utiliza
    // Optional de java.util y no emplees lambdas, Streams, referencias a métodos ni
    // métodos funcionales de colecciones. -> generado por IA
    /**
     * Obtiene un libro por su ID.
     *
     * @param id ID del libro a obtener.
     * @return DTO con los datos del libro obtenido.
     * @throws ResourceNotFoundException si el libro no existe.
     */
    @Override
    public BookResponseDTO obtenerLibroPorId(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el libro con ID: " + id);
        }
        Book book = optionalBook.get();
        return BookMapper.toResponseDTO(book);
    }

    // dame un método privado llamado validarPaginacion(int page, int size) que
    // devuelva el tamaño de página validado; si page es negativo lanza
    // ResponseStatusException con HttpStatus.BAD_REQUEST y un mensaje claro en
    // español; si size es menor o igual a cero lanza ResponseStatusException con
    // HttpStatus.BAD_REQUEST y un mensaje claro en español; si size es mayor que
    // 100 devuelve 100; en cualquier otro caso devuelve size. -> generado por IA
    /**
     * Valida los parámetros de paginación y limita el tamaño máximo.
     *
     * @param page Número de página.
     * @param size Tamaño solicitado.
     * @return Tamaño de página validado.
     */
    private int validarPaginacion(int page, int size) {
        if (page < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El número de página no puede ser negativo.");
        }

        if (size <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El tamaño de página debe ser mayor que cero.");
        }

        if (size > 100) {
            return 100;
        }

        return size;
    }

    // dame un método privado llamado validarOrdenacion(String sort) que devuelva
    // Sort; conserva el valor predeterminado "title,asc", valida el formato, los
    // campos permitidos (title, isbn, publishedYear, availableStock y totalStock) y
    // las direcciones asc y desc; conserva exactamente los mensajes y excepciones
    // ResponseStatusException con HttpStatus.BAD_REQUEST; añade siempre id como
    // segundo criterio de ordenación ascendente; añade Javadoc en español y no
    // utilices lambdas, referencias a métodos ni Streams. -> generado por IA
    /**
     * Valida y construye el objeto Sort a partir del parámetro de ordenación.
     *
     * @param sort Parámetro de ordenación.
     * @return Objeto Sort construido.
     */
    private Sort validarOrdenacion(String sort) {
        if (sort == null || sort.isEmpty()) {
            sort = "title,asc";
        }

        String[] sortParams = sort.split(",", -1);

        if (sortParams.length < 1 || sortParams.length > 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El parámetro de ordenación debe tener uno o dos elementos.");
        }

        String field = sortParams[0];
        String direction = sortParams.length == 2 ? sortParams[1] : "asc";

        if (field.isEmpty() || !field.equals(field.trim()) || direction.isEmpty()
                || !direction.equals(direction.trim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El parámetro de ordenación contiene valores vacíos o espacios no permitidos.");
        }

        if (!field.equals("title")
                && !field.equals("isbn")
                && !field.equals("publishedYear")
                && !field.equals("availableStock")
                && !field.equals("totalStock")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo de ordenación no permitido.");
        }

        if (!direction.equals("asc")
                && !direction.equals("ASC")
                && !direction.equals("desc")
                && !direction.equals("DESC")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dirección de ordenación no permitida.");
        }

        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        return Sort.by(sortDirection, field)
                .and(Sort.by(Sort.Direction.ASC, "id"));
    }

    // dame un método privado llamado convertirPaginaLibros(Page<Book> booksPage,
    // Pageable pageable) que devuelva Page<BookResponseDTO>; utiliza ArrayList y un
    // bucle for tradicional para convertir cada libro mediante
    // BookMapper.toResponseDTO; conserva el total de elementos y el Pageable
    // original al construir PageImpl; añade Javadoc en español y no utilices
    // lambdas, referencias a métodos ni Streams. -> generado por IA
    /**
     * Convierte una página de libros a una página de DTOs de respuesta.
     *
     * @param booksPage Página de libros a convertir.
     * @param pageable  Información de paginación.
     * @return Página de DTOs de respuesta de libros.
     */
    private Page<BookResponseDTO> convertirPaginaLibros(Page<Book> booksPage, Pageable pageable) {
        List<BookResponseDTO> bookResponseDTOs = new ArrayList<>();
        for (Book book : booksPage.getContent()) {
            bookResponseDTOs.add(BookMapper.toResponseDTO(book));
        }
        return new PageImpl<>(bookResponseDTOs, pageable, booksPage.getTotalElements());
    }

    // dame un método privado llamado obtenerAutores(List<Long> authorIds) que
    // devuelva List<Author>; recorre los identificadores con un bucle for clásico,
    // busca cada autor con authorRepository.findById, comprueba con isEmpty() si no
    // existe y lanza ResourceNotFoundException, recupera cada autor con get(),
    // comprueba mediante otro bucle for clásico si su ID ya está en la nueva lista
    // y solo lo añade cuando no exista; añade Javadoc completo en español y no
    // utilices lambdas, Streams, referencias a métodos ni métodos funcionales de
    // colecciones. -> generado por IA
    /**
     * Obtiene una lista de autores a partir de sus IDs.
     *
     * @param authorIds Lista de IDs de autores.
     * @return Lista de entidades Author correspondientes a los IDs proporcionados.
     * @throws IllegalArgumentException si algún ID es nulo o algún autor no existe.
     */
    private List<Author> obtenerAutores(List<Long> authorIds) {
        List<Author> authors = new ArrayList<>();

        for (Long authorId : authorIds) {

            if (authorId == null) {
                throw new IllegalArgumentException("El ID del autor no puede ser nulo.");
            }

            Optional<Author> optionalAuthor = authorRepository.findById(authorId);

            if (optionalAuthor.isEmpty()) {
                throw new IllegalArgumentException("No existe el autor con ID: " + authorId);
            }

            Author author = optionalAuthor.get();
            boolean found = false;

            for (Author existingAuthor : authors) {
                if (existingAuthor.getId().equals(author.getId())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                authors.add(author);
            }
        }

        return authors;
    }

    // dame un método privado llamado validarLibroExistente(String title, String
    // isbn) que devuelva void; busca un libro mediante
    // bookRepository.findByTitle(title) y otro mediante
    // bookRepository.findByIsbn(isbn); si alguno de los dos Optional<Book>
    // devueltos no está vacío lanza ResourceConflictException con un mensaje claro
    // en español indicando que ya existe un libro con ese título o ISBN; añade
    // Javadoc completo en español y no utilices lambdas, Streams, referencias a
    // métodos ni operaciones en cascada. -> generado por IA
    /**
     * Valida si ya existe un libro con el mismo título o ISBN.
     *
     * @param title Título del libro a validar.
     * @param isbn  ISBN del libro a validar.
     * @throws ResourceConflictException si ya existe un libro con el mismo título o
     *                                   ISBN.
     */
    private void validarLibroExistente(String title, String isbn) {
        List<Book> books = bookRepository.findAll();

        for (Book existingBook : books) {

            if (existingBook.getTitle() != null
                    && existingBook.getTitle().equals(title)) {
                throw new ResourceConflictException(
                        "Ya existe un libro con el título: " + title);
            }

            if (existingBook.getIsbn() != null
                    && existingBook.getIsbn().equals(isbn)) {
                throw new ResourceConflictException(
                        "Ya existe un libro con el ISBN: " + isbn);
            }
        }
    }
}