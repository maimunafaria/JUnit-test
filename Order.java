package org.example;

import java.util.List;
import java.time.LocalDate;

public class Order {
    private int orderID;
    private List<Book> books;
    private double totalPrice;
    private String customerName;
    private String shippingAddress;
    private LocalDate orderDate;

    public Order(int orderID, List<Book> books, double totalPrice, String customerName, String shippingAddress, LocalDate orderDate) {
        this.orderID = orderID;
        this.books = books;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public List<Book> getBooks() {
        return books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
