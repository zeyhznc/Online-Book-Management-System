package bookmanagement.security.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bookmanagement.security.book.Book;
import bookmanagement.security.book.BookService;

public class CustomerController {
	
	   @Autowired
	    private CustomerService customerService;
	   
	   @Autowired
	    private BookService bookService;

	    @GetMapping("/profile")
	    public Optional<Customer> getCustomerProfile(@RequestParam String email) {
	        return customerService.findByEmail(email);
	    }

	    // Diğer controller metodları buraya eklenebilir: sepet işlemleri, ödeme işlemi vb.
	    
	    @PostMapping("/addToCart")
	    public void addToCart(@RequestParam String email, @RequestParam Long bookId) {
	        Optional<Customer> customer = customerService.findByEmail(email);
	        Book book = bookService.findById(bookId);
	        customerService.addToCart(customer, book);
	    }

	    @PostMapping("/removeFromCart")
	    public void removeFromCart(@RequestParam String email, @RequestParam Long bookId) {
	        Optional<Customer> customer = customerService.findByEmail(email);
	        Book book = bookService.findById(bookId);
	        customerService.removeFromCart(customer, book);
	    }

	    @PostMapping("/performPayment")
	    public void performPayment(@RequestParam String email) {
	        Optional<Customer> customer = customerService.findByEmail(email);
	        customerService.performPayment(customer);
	    }

}
