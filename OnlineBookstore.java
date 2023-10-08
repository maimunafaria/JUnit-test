package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OnlineBookstore {
    private List<Book> inventory;
    private List<Order> orders;
    private int orderCounter;

    public OnlineBookstore() {
        inventory = new ArrayList<>();
        orders = new ArrayList<>();
        orderCounter = 1;
    }

    public void addBook(Book book) {
        inventory.add(book);
    }

    public void removeBook(int bookID) {
        inventory.removeIf(book -> book.getBookID() == bookID);
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : inventory) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }

        // Print the search results
        if (result.isEmpty()) {
            System.out.println("No books found with title: " + title);
        } else {
            System.out.println("Books with title \"" + title + "\":");
            for (Book book : result) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }

        return result;
    }

    public List<Book> searchBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : inventory) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }

        // Print the search results
        if (result.isEmpty()) {
            System.out.println("No books found by author: " + author);
        } else {
            System.out.println("Books by author \"" + author + "\":");
            for (Book book : result) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }

        return result;
    }

    public List<Book> searchBooksByGenre(String genre) {
        List<Book> result = new ArrayList<>();
        for (Book book : inventory) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }

        // Print the search results
        if (result.isEmpty()) {
            System.out.println("No books found in genre: " + genre);
        } else {
            System.out.println("Books in genre \"" + genre + "\":");
            for (Book book : result) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }

        return result;
    }
    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        if (inventory.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            System.out.println("All Books :");
            for (Book book : inventory) {
                System.out.println("Book ID: " + book.getBookID());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Price: " + book.getPrice());
                System.out.println("___" );
                result.add(book);
            }
        }
        return result;
    }


    public Order checkout(ShoppingCart cart, String customerName, String shippingAddress, LocalDate orderDate) {
        double totalPrice = 0;
        for (Book book : cart.getBooks()) {
            totalPrice += book.getPrice();
        }
        Order order = new Order(orderCounter++, cart.getBooks(), totalPrice, customerName, shippingAddress, orderDate);
        orders.add(order);
        return order;
    }

    public double calculateTotalPrice(ShoppingCart cart) {
        double totalPrice = 0;
        for (Book book : cart.getBooks()) {
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }

    public void processPayment(Order order, String paymentInfo) {

        System.out.println("Payment for Order ID: " + order.getOrderID());
    }

    public String generateOrderConfirmation(Order order) {
        StringBuilder confirmation = new StringBuilder();
        confirmation.append("Order Confirmation\n");
        confirmation.append("Order ID: " + order.getOrderID() + "\n");
        confirmation.append("Order Date: " + order.getOrderDate() + "\n");
        confirmation.append("Customer Name: " + order.getCustomerName() + "\n");
        confirmation.append("Shipping Address: " + order.getShippingAddress() + "\n");
        confirmation.append("Total Price: " + order.getTotalPrice() + "\n");
        confirmation.append("Books:\n");
        for (Book book : order.getBooks()) {
            confirmation.append( book.getTitle() + " by " + book.getAuthor() + "\n");
        }
        return confirmation.toString();
    }

    public String trackOrderStatus(int orderID) {
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                return "Order Status: Shipped";
            }
        }
        return "Order not found";
    }

    public List<Book> getInventory() {
        return inventory;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
