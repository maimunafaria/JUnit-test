import static org.junit.Assert.*;
import org.example.Book;
import org.example.OnlineBookstore;
import org.example.Order;
import org.example.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class OnlineBookstoreTest {
    private OnlineBookstore bookstore;

    @Before
    public void setUp() {
        bookstore = new OnlineBookstore();
    }

    @Test
    public void testAddBook() {
        Book book = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        bookstore.addBook(book);
        List<Book> inventory = bookstore.getInventory();
        assertEquals(1, inventory.size());
        assertEquals(book, inventory.get(0));
    }

    @Test
    public void testRemoveBook() {
        Book book = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        bookstore.addBook(book);
        bookstore.removeBook(1);
        List<Book> inventory = bookstore.getInventory();
        assertEquals(0, inventory.size());
    }

    @Test
    public void testSearchBooksByTitle() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        Book book2 = new Book(2, "Book 1", "Author 2", "Non-Fiction", 25.0);
        bookstore.addBook(book1);
        bookstore.addBook(book2);
        List<Book> result = bookstore.searchBooksByTitle("Book 1");
        assertEquals(2, result.size());
    }

    @Test
    public void testSearchBooksByAuthor() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        Book book2 = new Book(2, "Book 2", "Author 1", "Non-Fiction", 25.0);
        bookstore.addBook(book1);
        bookstore.addBook(book2);
        List<Book> result = bookstore.searchBooksByAuthor("Author 1");
        assertEquals(2, result.size());
    }

    @Test
    public void testSearchBooksByGenre() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        Book book2 = new Book(2, "Book 2", "Author 2", "Fiction", 25.0);
        bookstore.addBook(book1);
        bookstore.addBook(book2);
        List<Book> result = bookstore.searchBooksByGenre("Fiction");
        assertEquals(2, result.size());
    }

    @Test
    public void testCheckout() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        Book book2 = new Book(2, "Book 2", "Author 2", "Non-Fiction", 25.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);
        cart.addBook(book2);
        LocalDate orderDate = LocalDate.of(2023, 10, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);
        assertNotNull(order);
        assertEquals(1, bookstore.getOrders().size());
    }

    @Test
    public void testCalculateTotalPrice() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        Book book2 = new Book(2, "Book 2", "Author 2", "Non-Fiction", 25.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);
        cart.addBook(book2);
        double totalPrice = bookstore.calculateTotalPrice(cart);
        assertEquals(45.0, totalPrice, 0.01);
    }

    @Test
    public void testProcessPayment() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);
        LocalDate orderDate = LocalDate.of(2023, 10, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);
        assertNotNull(order);
        bookstore.processPayment(order, "Credit Card Info");
        assertEquals("Order Status: Shipped", bookstore.trackOrderStatus(order.getOrderID()));
    }

    @Test
    public void testGenerateOrderConfirmation() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);
        LocalDate orderDate = LocalDate.of(2023, 10, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);
        String confirmation = bookstore.generateOrderConfirmation(order);
        assertTrue(confirmation.contains("Order Confirmation"));
        assertTrue(confirmation.contains("Order ID: " + order.getOrderID()));
        assertTrue(confirmation.contains("Customer Name: " + order.getCustomerName()));
    }

    @Test
    public void testTrackOrderStatus() {
        Book book1 = new Book(1, "Book 1", "Author 1", "Fiction", 20.0);
        ShoppingCart cart = new ShoppingCart();
        cart.addBook(book1);
        LocalDate orderDate = LocalDate.of(2023, 10, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);
        assertEquals("Order Status: Shipped", bookstore.trackOrderStatus(order.getOrderID()));
    }
    @Test
    public void testEmptyInventory() {
        List<Book> result = bookstore.searchBooksByTitle("Book 1");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyInventoryOrder() {
        List<Book> result = bookstore.getAllBooks();

        ShoppingCart cart = new ShoppingCart();
        for (Book book : result) {
            cart.addBook(book);
        }
        LocalDate orderDate = LocalDate.of(2023, 10, 15);
        Order order = bookstore.checkout(cart, "Faria", "Azimpur", orderDate);

        assertTrue(result.isEmpty());
    }

}
