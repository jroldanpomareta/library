package com.accenture.library.repository.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.accenture.library.model.Book;

// Crea la interfaz pública IBookRepository en el paquete com.accenture.library.repository.interfaces para gestionar el acceso a datos de Book; extiende JpaRepository<Book, Long>, importa únicamente las clases necesarias y añade Javadoc en español que explique su finalidad; no declares métodos personalizados ni utilices lambdas, referencias a métodos o Streams. -> generado por IA
// Añade un método público llamado buscarLibros que devuelva Page<Book> y reciba String title, String author, String isbn, Long categoryId, Long authorId, Boolean available y Pageable pageable; utiliza @Query con JPQL y parámetros nombrados para aplicar cada filtro solo cuando su parámetro no sea null; title e isbn deben buscar coincidencias parciales sin distinguir mayúsculas y minúsculas, author debe buscar por firstName o lastName, categoryId debe filtrar por la categoría, authorId por los autores asociados y available true debe buscar libros con availableStock mayor que cero mientras que false debe buscar libros con availableStock igual a cero; combina todos los filtros cuando se informen, utiliza DISTINCT para evitar libros duplicados por las relaciones con autores e incluye una countQuery equivalente para que la paginación funcione correctamente; utiliza @Param en todos los parámetros, importa Page, Pageable, Query y Param, añade Javadoc completo en español y no utilices lambdas, referencias a métodos ni Streams. -> generado por IA
/**
 * Interfaz de repositorio para la entidad Book.
 * Proporciona métodos CRUD y de consulta para gestionar el acceso a datos de
 * libros en la base de datos.
 * Extiende JpaRepository para heredar funcionalidades básicas de persistencia.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public interface IBookRepository extends JpaRepository<Book, Long> {

    /**
     * Busca libros aplicando múltiples filtros opcionales y paginación.
     *
     * @param title      Título parcial del libro (opcional).
     * @param author     Nombre o apellido del autor (opcional).
     * @param isbn       ISBN parcial del libro (opcional).
     * @param categoryId ID de la categoría (opcional).
     * @param authorId   ID del autor (opcional).
     * @param available  Disponibilidad del libro: true para availableStock > 0,
     *                   false para availableStock = 0 (opcional).
     * @param pageable   Información de paginación y ordenamiento.
     * @return Página de libros que cumplen con los filtros aplicados.
     */
    @Query(value = "SELECT DISTINCT b FROM Book b " +
            "LEFT JOIN b.authors a " +
            "WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:author IS NULL OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :author, '%')) OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :author, '%'))) "
            +
            "AND (:isbn IS NULL OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :isbn, '%'))) " +
            "AND (:categoryId IS NULL OR b.category.id = :categoryId) " +
            "AND (:authorId IS NULL OR a.id = :authorId) " +
            "AND (:available IS NULL OR (:available = TRUE AND b.availableStock > 0) OR (:available = FALSE AND b.availableStock = 0))", countQuery = "SELECT COUNT(DISTINCT b) FROM Book b "
                    +
                    "LEFT JOIN b.authors a " +
                    "WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
                    "AND (:author IS NULL OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :author, '%')) OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :author, '%'))) "
                    +
                    "AND (:isbn IS NULL OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :isbn, '%'))) " +
                    "AND (:categoryId IS NULL OR b.category.id = :categoryId) " +
                    "AND (:authorId IS NULL OR a.id = :authorId) " +
                    "AND (:available IS NULL OR (:available = TRUE AND b.availableStock > 0) OR (:available = FALSE AND b.availableStock = 0))")
    Page<Book> buscarLibros(
            @Param("title") String title,
            @Param("author") String author,
            @Param("isbn") String isbn,
            @Param("categoryId") Long categoryId,
            @Param("authorId") Long authorId,
            @Param("available") Boolean available,
            Pageable pageable);
}