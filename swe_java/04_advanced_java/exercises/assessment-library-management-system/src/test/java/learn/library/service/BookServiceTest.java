package learn.library.service;

import learn.library.data.BookFileRepository;
import learn.library.data.DataException;
import learn.library.model.Book;
import learn.library.model.CategoryNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @TempDir
    Path tempDir;

    BookService service;

    @BeforeEach
    void setUp() {
        BookFileRepository repository = new BookFileRepository(
                tempDir.resolve("books-service-test.txt").toString());
        service = new BookService(repository);
    }

    @Test
    void addAcceptsValidBook() throws DataException {
        Book book = validBook("ISBN-200", 1, 1);

        BookResult result = service.add(book);

        assertTrue(result.isSuccess());
        assertEquals(book, result.getPayload());
    }

    @Test
    void addRejectsShelfOutsideAllowedRange() throws DataException {
        Book book = validBook("ISBN-201", 251, 1);

        BookResult result = service.add(book);

        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains(
                "Shelf Number must be between 1 and 250."));
    }

    @Test
    void addRejectsCurrentYear() throws DataException {
        Book book = validBook("ISBN-202", 2, 2);
        book.setYearPublished(Year.now().getValue());

        BookResult result = service.add(book);

        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains(
                "Year Published must be in the past."));
    }

    @Test
    void addRejectsDuplicateIsbn() throws DataException {
        service.add(validBook("ISBN-203", 3, 3));
        Book duplicate = validBook("ISBN-203", 4, 4);

        BookResult result = service.add(duplicate);

        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("ISBN must be unique."));
    }

    @Test
    void addRejectsOccupiedLocation() throws DataException {
        service.add(validBook("ISBN-204", 5, 5));
        Book sameLocation = validBook("ISBN-205", 5, 5);

        BookResult result = service.add(sameLocation);

        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains(
                "That category, shelf, and position already contain a book."));
    }

    @Test
    void updateAllowsOriginalIsbnAndLocation() throws DataException {
        Book original = validBook("ISBN-206", 6, 6);
        assertTrue(service.add(original).isSuccess());
        Book updated = validBook("ISBN-206", 6, 6);
        updated.setAuthor("Changed Author");

        BookResult result = service.update(original, updated);

        assertTrue(result.isSuccess());
        assertEquals("Changed Author", result.getPayload().getAuthor());
    }

    private Book validBook(String isbn, int shelf, int position) {
        return new Book(CategoryNames.ROMANCE, shelf, position,
                2000, "Valid Author", isbn);
    }
}
