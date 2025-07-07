package repositories;

import models.Book;

// Interfaz para definir operaciones de repositorio de libros (Principio SOLID: OCP y DIP)
public interface BookRepository {
    // Método para añadir un libro (Principio SOLID: ISP - Método específico y minimalista)
    void addBook(Book book);

    // Método para eliminar un libro por ISBN (Principio SOLID: SRP - Responsabilidad única)
    void removeBook(String isbn);

    // Método para buscar un libro por ISBN (Principio SOLID: LSP - Retorna un Book, compatible con futuras subclases)
    Book findBookByIsbn(String isbn);

    // Método para listar todos los libros (Principio SOLID: ISP - Podría segregarse si hay múltiples formas de listar)
    void getAllBooks();
}