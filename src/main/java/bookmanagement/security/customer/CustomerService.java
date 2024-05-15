package bookmanagement.security.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import bookmanagement.security.book.Book;

public class CustomerService {
	
    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    
    public void addToCart(Optional<Customer> customer, Book book) {
        // Müşterinin sepetine kitap eklemesi
    	   customer.ifPresent(c -> {
    	        // Müşterinin mevcut sepetini alın
    		   List<Book> cart = c.getCart();

    	        // Kitabı müşterinin sepetine ekleyin
    	        cart.add(book);

    	        // Müşterinin sepetini güncelleyin
    	        c.setCart(cart);

    	        // Müşteriyi veritabanına kaydedin
    	        customerRepository.save(c);
    	    });
    }

    public void removeFromCart(Optional<Customer> customer, Book book) {
        // Müşterinin sepetinden kitap kaldırması
    	


        // Optional<Customer> tipinden Customer nesnesini çözümle
        customer.ifPresent(c -> {
            // Müşterinin mevcut sepetini alın
            List<Book> cart = c.getCart();

            // Eğer müşterinin sepeti null değilse ve kitap sepette ise, kitabı sepetten kaldırın
            if (cart != null && cart.contains(book)) {
                cart.remove(book);

                // Müşterinin sepetini güncelleyin
                c.setCart(cart);

                // Müşteriyi veritabanına kaydedin
                customerRepository.save(c);
            }
        });
    }

    public void performPayment(Optional<Customer> customer) {
        // Ödeme işlemi
    	

        // Optional<Customer> tipinden Customer nesnesini çözümle
        customer.ifPresent(c -> {
            // Müşterinin sepetini alın
            List<Book> cart = c.getCart();

            // Sepetin toplam tutarını hesaplayın
            BigDecimal totalAmount = calculateTotalAmount(cart);

            // Ödeme işlemini simüle etmek için gerekli adımları gerçekleştirin
            // Örneğin, ödeme işlemi başarılı olursa sepetteki kitapları satın alınmış olarak işaretle veya sepeti temizleyin

            // Ödeme işlemi tamamlandığında, müşterinin sepetini güncelleyin
            c.setCart(new ArrayList<>());

            // Müşteriyi veritabanına kaydedin
            customerRepository.save(c);
        });
    }

    // Sepetin toplam tutarını hesaplamak için yardımcı bir metod
    private BigDecimal calculateTotalAmount(List<Book> cart) {
        // Sepetin toplam tutarını hesaplayın
        // Örneğin, sepetteki her kitabın fiyatını toplayabilirsiniz
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Book book : cart) {
            totalAmount = totalAmount.add(book.getPrice());
        }
        return totalAmount;
    	
    }


}
