package com.accenture.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// Crea la entidad JPA Category asociada a la tabla "category", con id Long autogenerado mediante IDENTITY, name obligatorio, único y de longitud máxima 100, description opcional de longitud máxima 500 y books como relación bidireccional OneToMany con Book mediante mappedBy = "category" y FetchType.LAZY; no utilices CascadeType.ALL ni CascadeType.REMOVE, ni añadas cascadas innecesarias; utiliza Lombok selectivamente (@Getter, @Setter y @NoArgsConstructor), inicializa books con ArrayList, evita @Data, lambdas, referencias a métodos y Streams, respeta el esquema SQL existente e incluye Javadoc de clase. -> generado por IA
//pon el javadoc en español -> generado por IA
/**
 * Representa una categoría en el sistema de biblioteca.
 * Esta entidad está asociada a la tabla "category" en la base de datos.
 * Incluye campos para el nombre y la descripción de la categoría.
 * La relación con los libros se define como una asociación bidireccional OneToMany.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private java.util.List<Book> books = new java.util.ArrayList<>();
}