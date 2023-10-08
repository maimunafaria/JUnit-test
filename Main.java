package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        OnlineBookstore bookstore = new OnlineBookstore();

        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        Book book2 = new Book(2, "Book 2", "Author 2", "Non-Fiction", 25.0);
        Book book3 = new Book(3, "Book 3", "Author 2", "Fiction", 30.0);
        bookstore.addBook(book1);
        bookstore.addBook(book2);
        bookstore.addBook(book3);
        bookstore.removeBook(1);
        bookstore.searchBooksByAuthor("Author 2");
        bookstore.searchBooksByGenre("Fiction");
        bookstore.searchBooksByTitle("Book 1");
        bookstore.getAllBooks();

        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book2);

        LocalDate orderDate = LocalDate.of(2023, 10, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);

        double totalPrice = bookstore.calculateTotalPrice(cart);

        bookstore.processPayment(order, "Payment Info");

        String confirmation = bookstore.generateOrderConfirmation(order);
        System.out.println(confirmation);

        String orderStatus = bookstore.trackOrderStatus(order.getOrderID());
        System.out.println(orderStatus);
    }
}
