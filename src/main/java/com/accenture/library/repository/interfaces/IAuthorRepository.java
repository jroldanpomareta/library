package com.accenture.library.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.library.model.Author;

// Crea la interfaz pública IAuthorRepository en el paquete com.accenture.library.repository.interfaces para gestionar el acceso a datos de Author; extiende JpaRepository<Author, Long>, importa únicamente las clases necesarias y añade Javadoc en español que explique su finalidad; no declares métodos personalizados ni utilices lambdas, referencias a métodos o Streams. -> generado por IA

/**
 * Interfaz de repositorio para la entidad Author.
 * Proporciona métodos CRUD y de consulta para gestionar el acceso a datos de autores en la base de datos.
 * Extiende JpaRepository para heredar funcionalidades básicas de persistencia.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public interface IAuthorRepository extends JpaRepository<Author, Long> {
}