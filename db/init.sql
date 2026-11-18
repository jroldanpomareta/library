-- Active: 1780738611428@@127.0.0.1@3306@library

-- =========================================================
-- LIBRARY - Script de inicialización de la base de datos
-- MySQL 8.0+
-- =========================================================

CREATE DATABASE IF NOT EXISTS library
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE library;

-- Desactivar temporalmente las comprobaciones de claves foráneas
-- para permitir reinicializar las tablas en cualquier orden.
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS loan;
DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS category;

SET FOREIGN_KEY_CHECKS = 1;

-- =========================================================
-- TABLA: category
-- =========================================================

CREATE TABLE category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT uk_category_name UNIQUE (name)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- =========================================================
-- TABLA: author
-- =========================================================

CREATE TABLE author (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nationality VARCHAR(100),
    CONSTRAINT pk_author PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- =========================================================
-- TABLA: book
-- =========================================================

CREATE TABLE book (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    published_year INT,
    available_stock INT NOT NULL,
    total_stock INT NOT NULL,
    category_id BIGINT,
    CONSTRAINT pk_book PRIMARY KEY (id),
    CONSTRAINT uk_book_isbn UNIQUE (isbn),
    CONSTRAINT fk_book_category
        FOREIGN KEY (category_id)
        REFERENCES category (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- =========================================================
-- TABLA INTERMEDIA: book_author
-- =========================================================

CREATE TABLE book_author (
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT pk_book_author PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_book_author_book
        FOREIGN KEY (book_id)
        REFERENCES book (id),
    CONSTRAINT fk_book_author_author
        FOREIGN KEY (author_id)
        REFERENCES author (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- =========================================================
-- TABLA: loan
-- =========================================================

CREATE TABLE loan (
    id BIGINT NOT NULL AUTO_INCREMENT,
    loan_date DATE NOT NULL,
    expected_return_date DATE NOT NULL,
    actual_return_date DATE,
    status VARCHAR(20) NOT NULL,
    book_id BIGINT NOT NULL,
    CONSTRAINT pk_loan PRIMARY KEY (id),
    CONSTRAINT fk_loan_book
        FOREIGN KEY (book_id)
        REFERENCES book (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- =========================================================
-- DATOS INICIALES: CATEGORY (3)
-- =========================================================

INSERT INTO category (id, name, description) VALUES
(1, 'Novela', 'Narrativa de ficción'),
(2, 'Ciencia', 'Divulgación científica y técnica'),
(3, 'Historia', 'Obras históricas');

-- =========================================================
-- DATOS INICIALES: AUTHOR (5)
-- =========================================================

INSERT INTO author (id, first_name, last_name, nationality) VALUES
(1, 'Miguel', 'Cervantes', 'Española'),
(2, 'George', 'Orwell', 'Británica'),
(3, 'Isaac', 'Asimov', 'Estadounidense'),
(4, 'Mary', 'Shelley', 'Británica'),
(5, 'Ursula', 'Le Guin', 'Estadounidense');

-- =========================================================
-- DATOS INICIALES: BOOK (10)
-- =========================================================

INSERT INTO book
(id, title, isbn, published_year, available_stock, total_stock, category_id)
VALUES
(1, 'Don Quijote de la Mancha', '9788420412146', 1605, 2, 2, 1),
(2, '1984', '9780451524935', 1949, 1, 2, 1),
(3, 'Fundación', '9788497599244', 1951, 1, 2, 2),
(4, 'Frankenstein', '9780141439471', 1818, 2, 2, 1),
(5, 'Los desposeídos', '9780061054884', 1974, 1, 1, 2),
(6, 'El fin de la eternidad', '9780553292047', 1955, 0, 1, 2),
(7, 'Historia de dos ciudades', '9780141439600', 1859, 2, 2, 1),
(8, 'Libro sin categoría', '9780000000008', 2020, 1, 1, NULL),
(9, 'Libro sin autores', '9780000000009', 2021, 1, 1, 1),
(10, 'Libro sin préstamos', '9780000000010', 2022, 1, 1, 2);

-- =========================================================
-- RELACIÓN BOOK_AUTHOR
-- =========================================================

INSERT INTO book_author (book_id, author_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(5, 5),
(6, 3),
(7, 1),
(7, 2),
(8, 5);

-- =========================================================
-- DATOS INICIALES: LOAN (5)
-- =========================================================

INSERT INTO loan
(id, loan_date, expected_return_date, actual_return_date, status, book_id)
VALUES
(1, DATE_SUB(CURDATE(), INTERVAL 5 DAY),
    '2099-12-31', NULL, 'ACTIVE', 2),

(2, DATE_SUB(CURDATE(), INTERVAL 30 DAY),
    DATE_SUB(CURDATE(), INTERVAL 15 DAY), NULL, 'ACTIVE', 3),

(3, DATE_SUB(CURDATE(), INTERVAL 20 DAY),
    DATE_SUB(CURDATE(), INTERVAL 10 DAY),
    DATE_SUB(CURDATE(), INTERVAL 12 DAY), 'RETURNED', 1),

(4, DATE_SUB(CURDATE(), INTERVAL 8 DAY),
    DATE_SUB(CURDATE(), INTERVAL 2 DAY),
    DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'RETURNED', 4),

(5, DATE_SUB(CURDATE(), INTERVAL 3 DAY),
    '2099-12-31', NULL, 'ACTIVE', 6);
