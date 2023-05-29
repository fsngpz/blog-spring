package id.me.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The repository class of {@link User}.
 *
 * @author Ferdinand Sangap
 * @since 2023-05-27
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
