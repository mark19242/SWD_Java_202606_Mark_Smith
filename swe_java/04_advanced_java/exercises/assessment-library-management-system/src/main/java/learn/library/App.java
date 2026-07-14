package learn.library;

import learn.library.controller.Controller;
import learn.library.controller.View;
import learn.library.data.BookFileRepository;
import learn.library.data.BookRepository;
import learn.library.service.BookService;

/**
 * Application entry point (composition root).
 *
 * <p>This is your starting point. You will design and build the rest of the
 * application yourself — the model, the data-access (repository) layer, the
 * service layer, and the console user interface — following the three-layer
 * architecture described in the README.</p>
 *
 * <p>By the end, {@code main} should wire your layers together (dependency
 * injection) and start the program, for example:</p>
 *
 * <pre>
 *   BookRepository repository = new BookFileRepository("./data/books.txt");
 *   BookService service = new BookService(repository);
 *   View view = new View();
 *   Controller controller = new Controller(service, view);
 *   controller.run();
 * </pre>
 */
public class App {

    public static void main(String[] args) {

        // The repository reads and writes books to the data file.
        BookRepository repository =
                new BookFileRepository("./data/books.txt");

        // The service handles validation and business rules.
        BookService service = new BookService(repository);

        // The view handles console input and output.
        View view = new View();

        // The controller coordinates the view and service.
        Controller controller = new Controller(service, view);

        // Start the application.
        controller.run();
    }
}
