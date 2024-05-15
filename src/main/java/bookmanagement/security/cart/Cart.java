package bookmanagement.security.cart;

import java.util.ArrayList;
import java.util.List;

import bookmanagement.security.book.Book;
import bookmanagement.security.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne(mappedBy = "cart")
	    private Customer customer;

	    @ManyToMany
	    @JoinTable(
	            name = "cart_books",
	            joinColumns = @JoinColumn(name = "cart_id"),
	            inverseJoinColumns = @JoinColumn(name = "book_id")
	    )
	    private List<Book> books;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public void setCustomer(Customer customer) {
	        this.customer = customer;
	    }

	    public List<Book> getBooks() {
	        return books;
	    }

	    public void setBooks(List<Book> books) {
	        this.books = books;
	    }
	
		public void add(Book book) {
			
			if (books == null) {
	            books = new ArrayList<>();
			}
			books.add(book);			
		}
	
		public void remove(Book book) {
			
	        if (books != null) {
	            books.remove(book);
	        }			
		}

}
