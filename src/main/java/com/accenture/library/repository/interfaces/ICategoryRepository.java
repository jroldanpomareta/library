package com.accenture.library.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.library.model.Category;

// Crea la interfaz ICategoryRepository en el paquete com.accenture.library.repository.interfaces para gestionar el acceso a datos de Category y extiende JpaRepository<Category, Long>; añade Javadoc en español, no declares métodos personalizados, no utilices lambdas, referencias a métodos ni Streams y utiliza únicamente los imports necesarios. -> generado por IA
/**
 * Interfaz de repositorio para la entidad Category.
 * Proporciona métodos CRUD y de consulta para gestionar el acceso a datos de categorías en la base de datos.
 * Extiende JpaRepository para heredar funcionalidades básicas de persistencia.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    // Añade a ICategoryRepository el método boolean existsByName(String name), que permita comprobar si ya existe una categoría con el nombre indicado; incluye Javadoc completo en español explicando su finalidad, parámetro y valor de retorno, conserva la herencia de JpaRepository<Category, Long>, no modifiques otros elementos de la interfaz y no utilices lambdas, Streams ni referencias a métodos. -> generado por IA
    /**
     * Comprueba si existe una categoría con el nombre indicado.
     *
     * @param name nombre de la categoría que se desea comprobar
     * @return true si existe, false en caso contrario
     */
    boolean existsByName(String name);
}