package com.accenture.library.model;

// Crea la entidad JPA Author asociada a la tabla "author", con id Long autogenerado mediante IDENTITY, firstName obligatorio de longitud máxima 100, lastName obligatorio de longitud máxima 100 y nationality opcional de longitud máxima 100; establece una relación ManyToMany bidireccional con Book mediante la tabla intermedia "book_author", utilizando mappedBy en el lado propietario que se definirá en Book y FetchType.LAZY; no utilices CascadeType.ALL ni CascadeType.REMOVE ni cascadas innecesarias; utiliza Lombok selectivamente (@Getter, @Setter y @NoArgsConstructor), evita @Data, lambdas, referencias a métodos, Streams y Records; respeta exactamente el esquema SQL e incluye Javadoc de clase. -> generado por IA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//pon el javadoc en español -> generado por IA
/**
 * Representa un autor en el sistema de biblioteca.
 * Esta entidad está asociada a la tabla "author" en la base de datos.
 * Incluye campos para el nombre, apellido y nacionalidad del autor.
 * La relación con los libros se define como una asociación bidireccional
 * ManyToMany.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
}