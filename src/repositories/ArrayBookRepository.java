package repositories;

import java.util.ArrayList;
import java.util.List;
import models.Book;

// Implementación de BookRepository usando ArrayList (Principio SOLID: DIP - Depende de la abstracción BookRepository)
public class ArrayBookRepository implements BookRepository {
    // Lista para almacenar los libros (Principio SOLID: SRP - Solo maneja almacenamiento)
    private final List<Book> books = new ArrayList<>();

    // Constructor vacío (no se requiere inicialización especial)
    public ArrayBookRepository() {}

    // Añade un libro con validaciones (Principio SOLID: SRP - Validaciones son parte de la responsabilidad del repositorio)
    @Override
    public void addBook(Book book) {
        // Validación: libro no nulo
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        
        // Validación: ISBN no nulo o vacío
        if (book.getISBN() == null || book.getISBN().isEmpty()) {
            throw new IllegalArgumentException("Book ISBN cannot be null or empty");
        }
        
        // Validación: ISBN único (Principio SOLID: ISP - La interfaz no fuerza esta regla, pero es necesaria aquí)
        if (containsBookWithIsbn(book.getISBN())) {
            throw new IllegalArgumentException("A book with ISBN " + book.getISBN() + " already exists");
        }
        
        // Añade el libro a la lista
        this.books.add(book);
    }

    // Elimina un libro por ISBN (Principio SOLID: SRP - Maneja solo la eliminación)
    @Override
    public void removeBook(String isbn) {
        // Validación: ISBN no nulo o vacío
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        
        // Busca el libro por ISBN
        Book bookToRemove = findBookByIsbn(isbn);
        
        // Validación: libro existe
        if (bookToRemove == null) {
            throw new IllegalArgumentException("The book with ISBN " + isbn + " does not exist.");
        }
        
        // Elimina el libro
        this.books.remove(bookToRemove);
    }

    // Busca un libro por ISBN (Principio SOLID: SRP - Solo busca)
    @Override
    public Book findBookByIsbn(String isbn) {
        // Validación: ISBN no nulo o vacío
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        
        // Recorre la lista para encontrar el libro (Ineficiente para grandes listas, pero cumple con LSP - Puede ser mejorado sin romper el contrato)
        for (Book book : this.books) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        
        // Retorna null si no se encuentra
        return null;
    }

    // Lista todos los libros (Principio SOLID: SRP roto aquí - Mezcla lógica de almacenamiento y presentación)
    @Override
    public void getAllBooks() {
        // Itera e imprime los libros (Debería solo retornar la lista)
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);  // Obtiene el libro en posición i
            System.out.println("\nISBN: " + book.getISBN()
                + "\nTitle: " + book.getTitle()
                + "\nAutor: " + book.getAutor()
                + "\nYear of publication: " + book.getYearPublication()
                + "\nAvailable: " + book.getAvailability()
                + "\n-------------------------------------");
        }
    }

    // Método auxiliar privado para verificar si un ISBN existe (Principio SOLID: SRP - Encapsula lógica de verificación)
    private boolean containsBookWithIsbn(String isbn) {
        return findBookByIsbn(isbn) != null;
    }
}