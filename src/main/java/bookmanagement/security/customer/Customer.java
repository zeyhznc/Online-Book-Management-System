package bookmanagement.security.customer;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bookmanagement.security.book.Book;
import bookmanagement.security.cart.Cart;
import bookmanagement.security.token.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_customer")
public class Customer implements UserDetails{
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "_customer_seq", allocationSize = 1)
	private Integer customer_id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String pswd;
		
	
	@OneToMany(mappedBy = "customer")
	private List<Token> tokens;
	
	private List<Book> cart;
	

	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*
		 * should return a list of roles like USER and ADMIN
		 */
	    return role.getAuthorities();
	}
	
	
	
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public String getPassword() {
		return pswd;
	}

	public List<Book> getCart() {
		return cart;
	}
	
	
	
}
