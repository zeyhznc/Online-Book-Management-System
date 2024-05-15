package bookmanagement.security.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

//	@Query("SELECT t FROM Token t WHERE t.user.id = :userId AND t.valid = true")

  @Query(value = """
      select t from Token t inner join Customer c
      on t.id = c.customer_id
      where c.customer_id = :id and (t.expired = false or t.revoked = false)
      """)
  List<Token> findAllValidTokenByUser(Integer id);

  Optional<Token> findByToken(String token);
}
