package com.accenture.library.model;

// Crea desde cero la entidad JPA Book asociada a la tabla "book", respetando exactamente el esquema MySQL: id Long autogenerado mediante IDENTITY, title String obligatorio de longitud 150, isbn String obligatorio único de longitud 20, publishedYear Integer opcional, availableStock Integer obligatorio y totalStock Integer obligatorio; establece una relación ManyToOne opcional con Category mediante @JoinColumn(name = "category_id") y FetchType.LAZY; establece una relación ManyToMany propietaria con Author mediante @JoinTable(name = "book_author"), joinColumns book_id e inverseJoinColumns author_id, FetchType.LAZY; establece una relación OneToMany con Loan mediante mappedBy = "book" y FetchType.LAZY; no utilices cascadas, especialmente CascadeType.ALL o CascadeType.REMOVE; utiliza Lombok selectivamente (@Getter, @Setter y @NoArgsConstructor), inicializa las colecciones con ArrayList, no incluyas constructores con parámetros innecesarios, evita @Data, lambdas, referencias a métodos, Streams y Records; añade Javadoc completo en español. -> generado por IA

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//pon el javadoc en español -> generado por IA
/**
 * Representa un libro en el sistema de biblioteca.
 * Esta entidad está asociada a la tabla "book" en la base de datos.
 * Incluye campos para el título, ISBN, año de publicación, stock disponible y stock total del libro.
 * La relación con la categoría se define como ManyToOne opcional,
 * la relación con los autores se define como ManyToMany propietaria,
 * y la relación con los préstamos se define como OneToMany.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "published_year")
    private Integer publishedYear;

    @Column(name = "available_stock", nullable = false)
    private Integer availableStock;

    @Column(name = "total_stock", nullable = false)
    private Integer totalStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();
}