package services;

import models.Book;
import repositories.BookRepository;

// Clase que gestiona préstamos y devoluciones de libros (Principio SOLID: SRP - Solo maneja lógica de préstamos)
public class LoanManager {
    // Dependencia inyectada (Principio SOLID: DIP - Depende de la abstracción BookRepository)
    private final BookRepository repository;

    // Constructor con validación (Principio SOLID: SRP - Validación de dependencias)
    public LoanManager(BookRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repositorio no puede ser nulo");
        }
        this.repository = repository;
    }

    // Método para prestar un libro (Principio SOLID: SRP - Lógica única de préstamo)
    public void lendBook(String isbn) {
        // Busca el libro por ISBN
        Book book = repository.findBookByIsbn(isbn);
        
        // Validación: libro existe
        if (book == null) {
            throw new IllegalArgumentException("Libro con ISBN " + isbn + " no existe");
        }
        
        // Validación: libro disponible
        if (!book.getAvailability()) {
            throw new IllegalStateException("Libro con ISBN " + isbn + " ya está prestado");
        }
        
        // Marca el libro como no disponible
        book.setAvailability(false);
    }

    // Método para devolver un libro (Principio SOLID: SRP - Lógica única de devolución)
    public void returnBook(String isbn) {
        // Busca el libro por ISBN
        Book book = repository.findBookByIsbn(isbn);
        
        // Validación: libro existe
        if (book == null) {
            throw new IllegalArgumentException("Libro con ISBN " + isbn + " no existe");
        }
        
        // Validación: libro prestado
        if (book.getAvailability()) {
            throw new IllegalStateException("Libro con ISBN " + isbn + " no estaba prestado");
        }
        
        // Marca el libro como disponible
        book.setAvailability(true);
    }
}