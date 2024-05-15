package bookmanagement.security.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bookmanagement.security.book.Book;
import bookmanagement.security.book.BookRepository;
import bookmanagement.security.customer.Customer;
import bookmanagement.security.customer.CustomerRepository;

public class CartService {
	
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public void addToCart(Long customerId, Long bookId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));
        Book book = bookRepository.findById(bookId);

        Cart cart = (Cart) customer.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
        }

        cart.add(book);
        cartRepository.save(cart);
    }

    public void removeFromCart(Long customerId, Long bookId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));
        Book book = bookRepository.findById(bookId);

        Cart cart = (Cart) customer.getCart();
        if (cart != null) {
            cart.remove(book);
            cartRepository.save(cart);
        }
    }

    public List<Book> getCartBooks(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));
        Cart cart = (Cart) customer.getCart();
        if (cart != null) {
            return cart.getBooks();
        }
        return null;
    }

}
