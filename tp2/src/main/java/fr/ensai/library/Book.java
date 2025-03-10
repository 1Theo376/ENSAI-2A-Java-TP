package fr.ensai.library;

/**
 * Represents a book.
 */
public class Book extends Item {

    private String isbn;
    private Author author;

    public Book(String title, int year, int pageCount, String isbn, Author author) {
        super(title, year, pageCount);
        this.isbn = isbn;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return super.toString() + ", ISBN: " + isbn + ", Author: " + author.getName();
    }

}