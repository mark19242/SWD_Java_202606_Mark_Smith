package learn.library.data;

import learn.library.model.Book;
import learn.library.model.CategoryNames;

import java.util.List;

public interface BookRepository {

    List<Book> findAll() throws DataException;

    List<Book> findByCategory(CategoryNames category) throws DataException;

    Book findByLocation(CategoryNames category, int shelfNumber, int position) throws DataException;

    Book findByIsbn(String isbn) throws DataException;

    Book add(Book book) throws DataException;

    boolean update(Book original, Book updated) throws DataException;

    boolean delete(Book book) throws DataException;
}
