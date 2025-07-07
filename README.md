# 📚 Sistema de Gestión de Bibliotecas en Java

Un proyecto práctico para aplicar principios **SOLID**, manejo de colecciones y excepciones en Java.

## 🎯 Objetivo
Desarrollar un sistema modular de biblioteca que demuestre:
- Implementación de **principios SOLID**
- Uso de colecciones (`ArrayList` y arreglos estáticos)
- Manejo robusto de excepciones
- Diseño extensible con interfaces

## 🛠 Requisitos del Sistema

### 📖 1. Estructura Básica
#### **Clase `Libro` (Book)**
```java
// Atributos:
// ● ISBN (String, único) - Clave primaria
// ● Título (String) - Validado (no vacío)
// ● Autor (String) - Validado (no vacío)
// ● Año de publicación (int) - Validado (> 0)
// ● Disponibilidad (boolean) - Default: true

Repositorios de Libros

// Interfaz BookRepository:
// ○ addBook(Book) → Valida ISBN único
// ○ removeBook(String isbn) → Lanza excepción si no existe
// ○ findBook(String isbn) → Búsqueda eficiente
// ○ getAllBooks() → Listado completo

// Implementaciones:
// 1. ArrayListBookRepository → Basado en ArrayList (dinámico)
// 2. ArrayBookRepository → Arreglo estático (tamaño fijo)

Gestor de Préstamos (LoanManager)

// Funcionalidades clave:
// ● prestarLibro(isbn) → Valida disponibilidad
// ● devolverLibro(isbn) → Actualiza estado
// ● Validaciones: 
//   - No prestar ya prestado (!disponible)
//   - ISBN existente

✨ 2. Principios SOLID
Principio	Implementación
SRP	BookRepository solo almacenamiento, LoanManager solo préstamos
OCP	Interfaces como BookFilter para filtros extensibles (autor, año, etc.)
LSP	Clase EBook extendible de Book sin romper funcionalidad
ISP	Interfaces pequeñas (BookRepository + LoanManager en lugar de una grande)
DIP	LibraryReportGenerator depende de BookRepository (abstracción)

⚠️ 3. Manejo de Excepciones

// Custom Exception:
public class LibraryException extends Exception { ... }

// Casos validados:
// ● ISBN duplicado (addBook)
// ● Libro no encontrado (removeBook/prestarLibro)
// ● Campos inválidos (año negativo, strings vacíos)
// ● Límite de capacidad (ArrayBookRepository)


📊 4. Colecciones y Streams

// ArrayListBookRepository:
private List<Book> books = new ArrayList<>();

// ArrayBookRepository:
private Book[] booksArray = new Book[MAX_SIZE];

// Uso de Streams (ejemplo):
public List<Book> getBooksByAuthor(String author) {
    return books.stream()
        .filter(b -> b.getAuthor().equals(author))
        .collect(Collectors.toList());
}

📦 Entrega

Plazo máximo: Lunes 7/07 - 9:30 hs
Formato:

🎥 Video en YouTube explicando:

Código fuente

Conceptos teóricos (SOLID, colecciones, excepciones)

📦 Código fuente:

Opción 1: Repositorio Git (recomendado)

Opción 2: Archivos comprimidos (ZIP)
# Gestion_de_Bibliotecas
