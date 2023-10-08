import org.example.Book;
import org.example.OnlineBookstore;
import org.example.Order;
import org.example.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class OnlineBookstoreTest1 {
    private OnlineBookstore bookstore;

    @Before
    public void setUp() {
        bookstore = new OnlineBookstore();
    }
    @Test
    public void testNegativeMonthInOrderDate() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);

        LocalDate orderDate = LocalDate.of(2023, -1, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);

        assertNull(order);
    }

    @Test
    public void testNegativeTotalPrice() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", -20.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);

        double totalPrice = bookstore.calculateTotalPrice(cart);

        assertTrue(totalPrice > 0);
    }

    @Test
    public void testInvalidPaymentInfo() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);
        LocalDate orderDate = LocalDate.of(2023, 10, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);

        bookstore.processPayment(order, "");

        assertNotEquals("Order Status: Shipped", bookstore.trackOrderStatus(order.getOrderID()));
    }


}
