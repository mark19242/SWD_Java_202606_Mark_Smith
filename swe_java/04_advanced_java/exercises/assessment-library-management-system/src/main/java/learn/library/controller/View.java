package learn.library.controller;

import learn.library.model.Book;
import learn.library.model.CategoryNames;
import learn.library.service.BookResult;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

/**
 * Owns all console input and output so the controller can focus on application flow.
 */
public class View {

    private final Scanner console = new Scanner(System.in);

    public void displayWelcome() {
        System.out.println("""
          __________
         /         /|
        /_________/ |
        |         | |
        | LIBRARY | /
        |_________|/
        """);

        System.out.println("Welcome to the Library Management System");
        System.out.println("========================================");
    }

    public MenuOption selectMenuOption() {
        System.out.println("\nMain Menu");
        System.out.println("=========");
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%d. %s%n", option.getValue(), option.getLabel());
        }

        int selection = readInt("Select [0-4]: ", 0, 4);
        return MenuOption.fromValue(selection);
    }

    public CategoryNames chooseCategory() {
        System.out.println("\nCategories");
        System.out.println("==========");
        CategoryNames[] categories = CategoryNames.values();

        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d. %s%n", i + 1, categories[i].getDisplayName());
        }

        int selection = readInt("Select a category: ", 1, categories.length);
        return categories[selection - 1];
    }

    public int readShelfNumber() {
        return readInt("Shelf Number: ", 1, 250);
    }

    public int readPosition() {
        return readInt("Position: ", 1, 250);
    }

    public Book makeBook() {
        System.out.println("\nAdd a Book");
        System.out.println("==========");

        CategoryNames category = chooseCategory();
        int shelfNumber = readShelfNumber();
        int position = readPosition();
        String author = readRequiredString("Author: ");
        int yearPublished = readInt("Year Published: ", 1, Year.now().getValue() - 1);
        String isbn = readRequiredString("ISBN: ");

        return new Book(category, shelfNumber, position, yearPublished, author, isbn);
    }

    public Book updateBook(Book original) {
        System.out.println("\nEditing " + original.getLocationKey());
        System.out.println("Press [Enter] to keep the original value.");

        CategoryNames category = readUpdatedCategory(original.getCategory());
        int shelfNumber = readUpdatedInt("Shelf Number", original.getShelfNumber(), 1, 250);
        int position = readUpdatedInt("Position", original.getPosition(), 1, 250);
        String author = readUpdatedString("Author", original.getAuthor());
        int year = readUpdatedInt("Year Published", original.getYearPublished(),
                1, Year.now().getValue() - 1);
        String isbn = readUpdatedString("ISBN", original.getIsbn());

        return new Book(category, shelfNumber, position, year, author, isbn);
    }

    public void displayBooks(CategoryNames category, List<Book> books) {
        System.out.println("\nBooks in " + category.getDisplayName());
        if (books.isEmpty()) {
            System.out.println("No books were found.");
            return;
        }

        System.out.printf("%-6s %-4s %-6s %-28s %s%n",
                "Shelf", "Pos", "Year", "Author", "ISBN");
        for (Book book : books) {
            System.out.printf("%-6d %-4d %-6d %-28s %s%n",
                    book.getShelfNumber(),
                    book.getPosition(),
                    book.getYearPublished(),
                    book.getAuthor(),
                    book.getIsbn());
        }
    }

    public void displayResult(BookResult result, String successMessage) {
        if (result.isSuccess()) {
            System.out.println("\n[Success]");
            System.out.println(successMessage);
        } else {
            displayErrors(result.getMessages());
        }
    }

    public void displayError(String message) {
        System.out.println("\n[Err]");
        System.out.println(message);
    }

    public void displayErrors(List<String> messages) {
        System.out.println("\n[Err]");
        messages.forEach(System.out::println);
    }

    public void displayGoodbye() {
        System.out.println("\nGoodbye.");
    }

    private CategoryNames readUpdatedCategory(CategoryNames original) {
        System.out.printf("Category (%s) - press Enter to keep it:%n",
                original.getDisplayName());
        CategoryNames[] categories = CategoryNames.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d. %s%n", i + 1, categories[i].getDisplayName());
        }

        while (true) {
            System.out.print("Selection: ");
            String value = console.nextLine().trim();
            if (value.isEmpty()) {
                return original;
            }

            try {
                int selection = Integer.parseInt(value);
                if (selection >= 1 && selection <= categories.length) {
                    return categories[selection - 1];
                }
            } catch (NumberFormatException ignored) {
                // The message below handles invalid text and invalid ranges the same way.
            }
            System.out.println("Please select a valid category number.");
        }
    }

    private int readUpdatedInt(String label, int original, int min, int max) {
        while (true) {
            System.out.printf("%s (%d): ", label, original);
            String value = console.nextLine().trim();
            if (value.isEmpty()) {
                return original;
            }

            try {
                int number = Integer.parseInt(value);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // Continue to the common validation message.
            }
            System.out.printf("Enter a number between %d and %d.%n", min, max);
        }
    }

    private String readUpdatedString(String label, String original) {
        while (true) {
            System.out.printf("%s (%s): ", label, original);
            String value = console.nextLine().trim();
            if (value.isEmpty()) {
                return original;
            }
            if (!value.contains("|")) {
                return value;
            }
            System.out.println("The | character is not allowed.");
        }
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = console.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // Continue to the common validation message.
            }
            System.out.printf("Enter a number between %d and %d.%n", min, max);
        }
    }

    private String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = console.nextLine().trim();
            if (!value.isEmpty() && !value.contains("|")) {
                return value;
            }
            System.out.println("A value is required and the | character is not allowed.");
        }
    }
}
