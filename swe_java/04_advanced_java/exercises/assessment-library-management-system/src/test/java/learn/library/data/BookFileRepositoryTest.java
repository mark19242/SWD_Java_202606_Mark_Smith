package learn.library.data;

import learn.library.model.Book;
import learn.library.model.CategoryNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookFileRepositoryTest {

    @TempDir
    Path tempDir;

    BookFileRepository repository;

    @BeforeEach
    void setUp() {
        repository = new BookFileRepository(tempDir.resolve("books-test.txt").toString());
    }

    @Test
    void addAndFindByLocationWorks() throws DataException {
        Book expected = new Book(CategoryNames.ROMANCE, 1, 2,
                2001, "Test Author", "ISBN-100");

        repository.add(expected);
        Book actual = repository.findByLocation(CategoryNames.ROMANCE, 1, 2);

        assertEquals(expected, actual);
    }

    @Test
    void findByCategoryReturnsOnlyMatchingBooks() throws DataException {
        repository.add(new Book(CategoryNames.ROMANCE, 1, 1,
                2000, "Author One", "ISBN-101"));
        repository.add(new Book(CategoryNames.SELF_HELP, 1, 1,
                2002, "Author Two", "ISBN-102"));

        List<Book> result = repository.findByCategory(CategoryNames.ROMANCE);

        assertEquals(1, result.size());
        assertEquals("ISBN-101", result.get(0).getIsbn());
    }

    @Test
    void updateReplacesExistingBook() throws DataException {
        Book original = new Book(CategoryNames.ROMANCE, 2, 3,
                1999, "Original Author", "ISBN-103");
        Book updated = new Book(CategoryNames.ROMANCE, 4, 5,
                1999, "Updated Author", "ISBN-103");
        repository.add(original);

        boolean success = repository.update(original, updated);

        assertTrue(success);
        assertNull(repository.findByLocation(CategoryNames.ROMANCE, 2, 3));
        assertEquals(updated,
                repository.findByLocation(CategoryNames.ROMANCE, 4, 5));
    }

    @Test
    void deleteRemovesExistingBook() throws DataException {
        Book book = new Book(CategoryNames.BIOGRAPHY_MEMOIR, 3, 4,
                1985, "Test Author", "ISBN-104");
        repository.add(book);

        boolean success = repository.delete(book);

        assertTrue(success);
        assertNull(repository.findByIsbn("ISBN-104"));
    }

    @Test
    void malformedFileThrowsCustomException() throws Exception {
        Path file = tempDir.resolve("bad-books.txt");
        Files.writeString(file, "category|shelfNumber|position|yearPublished|author|isbn\nBAD DATA\n");
        BookFileRepository badRepository = new BookFileRepository(file.toString());

        assertThrows(DataException.class, badRepository::findAll);
    }
}
