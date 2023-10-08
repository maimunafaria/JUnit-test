package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Book> books;

    public ShoppingCart() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }
}
