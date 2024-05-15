package bookmanagement.security.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bookmanagement.security.book.Book;

public class CartController {
	
	/*
	 * /add: Müşterinin sepetine kitap eklemek için POST isteği alır. Müşteri ve kitap kimliği parametre olarak alınır ve bu bilgiler CartService'e iletir.
/remove: Müşterinin sepetinden kitap çıkarmak için POST isteği alır. Müşteri ve kitap kimliği parametre olarak alınır ve bu bilgiler CartService'e iletir.
/{customerId}: Belirli bir müşterinin sepetinde bulunan kitapları almak için GET isteği alır. Müşteri kimliği yol parametresi olarak alınır ve bu bilgi CartService'e iletir.
	 */
	
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestParam Long customerId, @RequestParam Long bookId) {
        cartService.addToCart(customerId, bookId);
    }

    @PostMapping("/remove")
    public void removeFromCart(@RequestParam Long customerId, @RequestParam Long bookId) {
        cartService.removeFromCart(customerId, bookId);
    }

    @GetMapping("/{customerId}")
    public List<Book> getCartBooks(@PathVariable Long customerId) {
        return cartService.getCartBooks(customerId);
    }

}
