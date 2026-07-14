package learn.library.controller;

import learn.library.data.DataException;
import learn.library.model.Book;
import learn.library.model.CategoryNames;
import learn.library.service.BookResult;
import learn.library.service.BookService;

import java.util.List;

/**
 * Coordinates menu choices between the view and service layers.
 */
public class Controller {

    private final BookService service;
    private final View view;

    public Controller(BookService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.displayWelcome();

        try {
            MenuOption option;
            do {
                option = view.selectMenuOption();
                switch (option) {
                    case FIND_BY_CATEGORY -> findBooksByCategory();
                    case ADD_BOOK -> addBook();
                    case UPDATE_BOOK -> updateBook();
                    case REMOVE_BOOK -> removeBook();
                    case EXIT -> view.displayGoodbye();
                }
            } while (option != MenuOption.EXIT);
        } catch (DataException ex) {
            view.displayError(ex.getMessage());
        }
    }

    private void findBooksByCategory() throws DataException {
        System.out.println("\nFind Books by Category");
        System.out.println("======================");
        CategoryNames category = view.chooseCategory();
        List<Book> books = service.findByCategory(category);
        view.displayBooks(category, books);
    }

    private void addBook() throws DataException {
        Book book = view.makeBook();
        BookResult result = service.add(book);
        view.displayResult(result, "Book " + book.getLocationKey() + " added.");
    }

    private void updateBook() throws DataException {
        System.out.println("\nUpdate a Book");
        System.out.println("=============");

        Book original = findBookFromLocation();
        if (original == null) {
            return;
        }

        Book updated = view.updateBook(original);
        BookResult result = service.update(original, updated);
        view.displayResult(result, "Book " + updated.getLocationKey() + " updated.");
    }

    private void removeBook() throws DataException {
        System.out.println("\nRemove a Book");
        System.out.println("=============");

        Book book = findBookFromLocation();
        if (book == null) {
            return;
        }

        BookResult result = service.delete(book);
        view.displayResult(result, "Book " + book.getLocationKey() + " removed.");
    }

    private Book findBookFromLocation() throws DataException {
        CategoryNames category = view.chooseCategory();
        int shelfNumber = view.readShelfNumber();
        int position = view.readPosition();

        Book book = service.findByLocation(category, shelfNumber, position);
        if (book == null) {
            view.displayError("There is no book "
                    + category.getDisplayName() + "-" + shelfNumber + "-" + position + ".");
        }
        return book;
    }
}
