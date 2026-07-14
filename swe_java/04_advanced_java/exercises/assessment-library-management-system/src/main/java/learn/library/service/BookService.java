package learn.library.service;

import learn.library.data.BookRepository;
import learn.library.data.DataException;
import learn.library.model.Book;
import learn.library.model.CategoryNames;

import java.time.Year;
import java.util.List;

/**
 * Enforces business rules before the repository is allowed to change the data file.
 */
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findByCategory(CategoryNames category) throws DataException {
        return repository.findByCategory(category);
    }

    public Book findByLocation(CategoryNames category, int shelfNumber, int position)
            throws DataException {
        return repository.findByLocation(category, shelfNumber, position);
    }

    public BookResult add(Book book) throws DataException {
        BookResult result = validate(book, null);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(book));
        return result;
    }

    public BookResult update(Book original, Book updated) throws DataException {
        BookResult result = validate(updated, original);
        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(original, updated)) {
            result.addMessage("The book could not be found for updating.");
            return result;
        }

        result.setPayload(updated);
        return result;
    }

    public BookResult delete(Book book) throws DataException {
        BookResult result = new BookResult();
        if (book == null || !repository.delete(book)) {
            result.addMessage("The book could not be found for removal.");
            return result;
        }

        result.setPayload(book);
        return result;
    }

    private BookResult validate(Book book, Book original) throws DataException {
        BookResult result = new BookResult();

        if (book == null) {
            result.addMessage("Book is required.");
            return result;
        }

        if (book.getCategory() == null) {
            result.addMessage("Category is required.");
        }

        if (book.getShelfNumber() < 1 || book.getShelfNumber() > 250) {
            result.addMessage("Shelf Number must be between 1 and 250.");
        }

        if (book.getPosition() < 1 || book.getPosition() > 250) {
            result.addMessage("Position must be between 1 and 250.");
        }

        if (book.getYearPublished() < 1
                || book.getYearPublished() >= Year.now().getValue()) {
            result.addMessage("Year Published must be in the past.");
        }

        if (book.getAuthor() == null || book.getAuthor().isBlank()
                || !book.getAuthor().matches(".*[A-Za-z].*")) {
            result.addMessage("Author is required and must be a valid string.");
        }

        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            result.addMessage("ISBN is required.");
        }

        if (!result.isSuccess()) {
            return result;
        }

        Book isbnMatch = repository.findByIsbn(book.getIsbn().trim());
        if (isbnMatch != null && !sameOriginal(isbnMatch, original)) {
            result.addMessage("ISBN must be unique.");
        }

        Book locationMatch = repository.findByLocation(
                book.getCategory(), book.getShelfNumber(), book.getPosition());
        if (locationMatch != null && !sameOriginal(locationMatch, original)) {
            result.addMessage("That category, shelf, and position already contain a book.");
        }

        return result;
    }

    private boolean sameOriginal(Book candidate, Book original) {
        if (candidate == null || original == null) {
            return false;
        }

        return candidate.getCategory() == original.getCategory()
                && candidate.getShelfNumber() == original.getShelfNumber()
                && candidate.getPosition() == original.getPosition()
                && candidate.getIsbn().equalsIgnoreCase(original.getIsbn());
    }
}
