package models;

// Clase que representa un libro (Principio SOLID: SRP - Modela solo los datos y reglas de un libro)
public class Book {
    // Atributos privados (Principio SOLID: Encapsulamiento)
    private String isbn;           // ISBN único del libro
    private String title;          // Título del libro
    private String autor;          // Autor del libro
    private int yearPublication;   // Año de publicación
    private boolean availability;  // Disponibilidad (true = disponible, false = prestado)

    // Constructor con validaciones (Principio SOLID: SRP - Validaciones son parte de la creación del objeto)
    public Book(String isbn, String title, String autor, int yearPublication) {
        // Validación: ISBN no nulo o vacío
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("El ISBN es obligatorio.");
        }
        // Validación: Título no nulo o vacío
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        // Validación: Autor no nulo o vacío
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("El autor es obligatorio.");
        }
        // Validación: Año no negativo
        if (yearPublication < 0) {
            throw new IllegalArgumentException("El año de publicación no puede ser negativo.");
        }

        // Asignación de valores si todo es válido
        this.isbn = isbn;
        this.title = title;
        this.autor = autor;
        this.yearPublication = yearPublication;
        this.availability = true; // Por defecto, el libro está disponible
    }

    // ----- Getters y Setters con validaciones ----- //

    // Getter para ISBN (Principio SOLID: Encapsulamiento - Acceso controlado)
    public String getISBN() {
        return this.isbn;
    }

    // Setter para ISBN (Principio SOLID: SRP - Validación centralizada)
    public void setISBN(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("El ISBN es obligatorio.");
        }
        this.isbn = isbn;
    }

    // Getter para título
    public String getTitle() {
        return this.title;
    }

    // Setter para título (typo en el nombre del método: debería ser "setTitle")
    public void setTítle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        this.title = title;
    }

    // Getter para autor
    public String getAutor() {
        return this.autor;
    }

    // Setter para autor
    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("El autor es obligatorio.");
        }
        this.autor = autor;
    }

    // Getter para año de publicación
    public int getYearPublication() {
        return this.yearPublication;
    }

    // Setter para año de publicación
    public void setYearPublication(int yearPublication) {
        if (yearPublication < 0) {
            throw new IllegalArgumentException("El año de publicación no puede ser negativo.");
        }
        this.yearPublication = yearPublication;
    }

    // Getter para disponibilidad
    public boolean getAvailability() {
        return this.availability;
    }

    // Setter para disponibilidad (sin validaciones, ya que es un booleano)
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}