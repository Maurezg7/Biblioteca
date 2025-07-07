package repositories;

import models.Book;

// Implementación de BookRepository usando un arreglo estático (Principio SOLID: DIP - Depende de la abstracción BookRepository)
public class ArrayListBookRepository implements BookRepository {
    // Tamaño máximo del arreglo (Principio SOLID: SRP - La limitación de capacidad es responsabilidad de esta implementación)
    private final int MAX_LIMIT = 100;
    
    // Arreglo para almacenar libros (Principio SOLID: SRP - Solo maneja almacenamiento)
    private final Book[] books;
    
    // Tamaño actual del arreglo (cantidad de libros almacenados)
    private int size;

    // Constructor inicializa el arreglo (Principio SOLID: SRP - Inicialización clara)
    public ArrayListBookRepository() {
        this.books = new Book[MAX_LIMIT]; // Arreglo de tamaño fijo
        this.size = 0; // Inicialmente vacío
    }

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
        
        // Validación: límite de capacidad
        if (size >= MAX_LIMIT) {
            throw new IllegalStateException("Maximum book limit reached");
        }
        
        // Validación: ISBN único (Principio SOLID: ISP - La interfaz no fuerza esta regla, pero es necesaria aquí)
        if (containsBookWithIsbn(book.getISBN())) {
            throw new IllegalArgumentException("A book with ISBN " + book.getISBN() + " already exists");
        }
        
        // Añade el libro al final del arreglo y aumenta el tamaño
        books[size++] = book;
    }

    // Elimina un libro por ISBN (Principio SOLID: SRP - Maneja solo la eliminación)
    @Override
    public void removeBook(String isbn) {
        // Validación: ISBN no nulo o vacío
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        
        // Busca el índice del libro por ISBN
        int index = findBookIndexByIsbn(isbn);
        
        // Validación: libro existe
        if (index == -1) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " does not exist");
        }
        
        // Elimina el libro desplazando los elementos posteriores (Principio SOLID: SRP - Lógica de eliminación encapsulada)
        System.arraycopy(books, index + 1, books, index, size - index - 1);
        books[--size] = null; // Elimina la referencia al último elemento y reduce el tamaño
    }

    // Busca un libro por ISBN (Principio SOLID: SRP - Solo busca)
    @Override
    public Book findBookByIsbn(String isbn) {
        // Validación: ISBN no nulo o vacío
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        
        // Busca el índice del libro
        int index = findBookIndexByIsbn(isbn);
        
        // Retorna el libro si existe, o null si no
        return index != -1 ? books[index] : null;
    }

    // Lista todos los libros (Principio SOLID: SRP roto aquí - Mezcla lógica de almacenamiento y presentación)
    @Override
    public void getAllBooks() {
        // Itera e imprime los libros (Debería solo retornar la lista)
        for (int i = 0; i < size; i++) {
            Book book = books[i];
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
        return findBookIndexByIsbn(isbn) != -1;
    }

    // Método auxiliar privado para buscar el índice de un libro por ISBN (Principio SOLID: SRP - Reutiliza lógica de búsqueda)
    private int findBookIndexByIsbn(String isbn) {
        // Búsqueda lineal (O(n)) - Podría optimizarse con un HashMap en otra implementación (Principio SOLID: OCP - Extensible sin modificar)
        for (int i = 0; i < size; i++) {
            if (books[i].getISBN().equals(isbn)) {
                return i;
            }
        }
        return -1; // Retorna -1 si no se encuentra
    }
}