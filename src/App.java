import models.Book;
import repositories.ArrayBookRepository;
import repositories.BookRepository;
import services.LoanManager;

// Clase principal que prueba el sistema de préstamos (Principio SOLID: SRP - Solo coordina pruebas)
public class App {
    public static void main(String[] args) {
        // Inicialización del repositorio y LoanManager (Principio SOLID: DIP - Usa abstracciones)
        BookRepository repository = new ArrayBookRepository(); // Implementación concreta
        LoanManager loanManager = new LoanManager(repository); // Inyección de dependencia

        try {
            // ----- Setup: Agregar libros de prueba ----- //
            repository.addBook(new Book("001", "Clean Code", "Robert Martin", 2008));
            repository.addBook(new Book("002", "Effective Java", "Joshua Bloch", 2017));

            // ----- Mostrar: Libros ----- //

            repository.getAllBooks();

            // ----- Caso 1: Préstamo exitoso ----- //
            loanManager.lendBook("001"); // Intenta prestar el libro con ISBN "001"
            System.out.println("[OK] Préstamo exitoso para 001");

            // ----- Caso 2: Préstamo duplicado (debe fallar) ----- //
            loanManager.lendBook("001"); // Intenta prestar el mismo libro otra vez
            System.err.println("[ERROR] No se detectó préstamo duplicado");

        } catch (Exception e) {
            // Manejo de errores genérico (Principio SOLID: OCP - Podría extenderse con excepciones específicas)
            System.err.println("[ERROR] Excepción inesperada: " + e.getMessage());
        }

        // ----- Caso 3: Devolución exitosa ----- //
        try {
            loanManager.returnBook("001"); // Devuelve el libro
            System.out.println("[OK] Devolución exitosa para 001");
        } catch (Exception e) {
            System.err.println("[ERROR] Devolución fallida: " + e.getMessage());
        }
    }
}