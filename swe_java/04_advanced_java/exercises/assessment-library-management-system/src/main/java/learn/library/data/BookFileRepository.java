package learn.library.data;

import learn.library.model.Book;
import learn.library.model.CategoryNames;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Stores books in a pipe-delimited text file so data survives application restarts.
 */
public class BookFileRepository implements BookRepository {

    private static final String HEADER = "category|shelfNumber|position|yearPublished|author|isbn";
    private static final String DELIMITER = "\\|";

    private final Path filePath;

    public BookFileRepository(String fileName) {
        this.filePath = Path.of(fileName);
    }

    @Override
    public List<Book> findAll() throws DataException {
        ensureFileExists();
        List<Book> books = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (!line.isEmpty()) {
                    books.add(deserialize(line, i + 1));
                }
            }
        } catch (IOException ex) {
            throw new DataException("Could not read the book data file.", ex);
        }

        books.sort(Comparator
                .comparing(Book::getCategory)
                .thenComparingInt(Book::getShelfNumber)
                .thenComparingInt(Book::getPosition));
        return books;
    }

    @Override
    public List<Book> findByCategory(CategoryNames category) throws DataException {
        return findAll().stream()
                .filter(book -> book.getCategory() == category)
                .toList();
    }

    @Override
    public Book findByLocation(CategoryNames category, int shelfNumber, int position)
            throws DataException {
        return findAll().stream()
                .filter(book -> book.getCategory() == category)
                .filter(book -> book.getShelfNumber() == shelfNumber)
                .filter(book -> book.getPosition() == position)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book findByIsbn(String isbn) throws DataException {
        if (isbn == null) {
            return null;
        }

        return findAll().stream()
                .filter(book -> isbn.equalsIgnoreCase(book.getIsbn()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book add(Book book) throws DataException {
        List<Book> books = findAll();
        books.add(book);
        writeAll(books);
        return book;
    }

    @Override
    public boolean update(Book original, Book updated) throws DataException {
        List<Book> books = findAll();

        for (int i = 0; i < books.size(); i++) {
            if (sameBook(books.get(i), original)) {
                books.set(i, updated);
                writeAll(books);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Book book) throws DataException {
        List<Book> books = findAll();
        boolean removed = books.removeIf(current -> sameBook(current, book));

        if (removed) {
            writeAll(books);
        }
        return removed;
    }

    private boolean sameBook(Book first, Book second) {
        return first.getCategory() == second.getCategory()
                && first.getShelfNumber() == second.getShelfNumber()
                && first.getPosition() == second.getPosition()
                && first.getIsbn().equalsIgnoreCase(second.getIsbn());
    }

    private Book deserialize(String line, int lineNumber) throws DataException {
        String[] fields = line.split(DELIMITER, -1);
        if (fields.length != 6) {
            throw new DataException("Invalid book data on line " + lineNumber + ".");
        }

        try {
            return new Book(
                    CategoryNames.valueOf(fields[0]),
                    Integer.parseInt(fields[1]),
                    Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]),
                    fields[4],
                    fields[5]
            );
        } catch (IllegalArgumentException ex) {
            throw new DataException("Invalid book data on line " + lineNumber + ".", ex);
        }
    }

    private String serialize(Book book) {
        return String.join("|",
                book.getCategory().name(),
                String.valueOf(book.getShelfNumber()),
                String.valueOf(book.getPosition()),
                String.valueOf(book.getYearPublished()),
                clean(book.getAuthor()),
                clean(book.getIsbn())
        );
    }

    private String clean(String value) {
        return value == null ? "" : value.replace("|", "").trim();
    }

    private void ensureFileExists() throws DataException {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (Files.notExists(filePath)) {
                Files.writeString(filePath, HEADER + System.lineSeparator(),
                        StandardOpenOption.CREATE_NEW);
            } else if (Files.size(filePath) == 0) {
                Files.writeString(filePath, HEADER + System.lineSeparator(),
                        StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException ex) {
            throw new DataException("Could not create the book data file.", ex);
        }
    }

    private void writeAll(List<Book> books) throws DataException {
        ensureFileExists();
        List<String> lines = new ArrayList<>();
        lines.add(HEADER);
        books.stream()
                .sorted(Comparator
                        .comparing(Book::getCategory)
                        .thenComparingInt(Book::getShelfNumber)
                        .thenComparingInt(Book::getPosition))
                .map(this::serialize)
                .forEach(lines::add);

        try {
            Files.write(filePath, lines,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);
        } catch (IOException ex) {
            throw new DataException("Could not save the book data file.", ex);
        }
    }
}
