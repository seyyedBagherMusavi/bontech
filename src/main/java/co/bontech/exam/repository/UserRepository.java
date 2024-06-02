package co.bontech.exam.repository;

import co.bontech.exam.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUserName(String login);

    @Query(
            value = """
                    UPDATE User u
                    SET u.inventory = u.inventory + :debit, u.version = u.version + 1
                     WHERE u.id = :userId AND u.version = :version
                    """)
    default void addDebit(Long userId, Integer debit, int version) {
    }
}
