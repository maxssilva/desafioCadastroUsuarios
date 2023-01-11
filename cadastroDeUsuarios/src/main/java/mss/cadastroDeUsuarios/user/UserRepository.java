package mss.cadastroDeUsuarios.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    List<User> findAll();

    Optional<User> findById(Long id);
}
