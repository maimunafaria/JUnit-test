package org.example;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private double price;

    public Book(int bookID, String title, String author, String genre, double price) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }
}
