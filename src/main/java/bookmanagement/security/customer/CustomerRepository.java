package bookmanagement.security.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	// find a user by email. because email is unique
	
	Optional<Customer> findByEmail(String email);

	void save(Optional<Customer> customer);

	Optional<Customer> findById(Long customerId);
	
}
