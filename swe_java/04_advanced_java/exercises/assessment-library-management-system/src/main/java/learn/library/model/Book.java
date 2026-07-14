package learn.library.model;

import java.util.Objects;

/**
 * Represents one book and its physical location in the library.
 */
public class Book {

    private CategoryNames category;
    private int shelfNumber;
    private int position;
    private int yearPublished;
    private String author;
    private String isbn;

    public Book() {
    }

    public Book(CategoryNames category, int shelfNumber, int position,
                int yearPublished, String author, String isbn) {
        this.category = category;
        this.shelfNumber = shelfNumber;
        this.position = position;
        this.yearPublished = yearPublished;
        this.author = author;
        this.isbn = isbn;
    }

    public CategoryNames getCategory() {
        return category;
    }

    public void setCategory(CategoryNames category) {
        this.category = category;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gives the location format used by success and error messages.
     */
    public String getLocationKey() {
        String categoryName = category == null ? "Unknown" : category.getDisplayName();
        return categoryName + "-" + shelfNumber + "-" + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return shelfNumber == book.shelfNumber
                && position == book.position
                && yearPublished == book.yearPublished
                && category == book.category
                && Objects.equals(author, book.author)
                && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, shelfNumber, position, yearPublished, author, isbn);
    }

    @Override
    public String toString() {
        return String.format("%s | Shelf %d | Position %d | %d | %s | %s",
                category, shelfNumber, position, yearPublished, author, isbn);
    }
}
