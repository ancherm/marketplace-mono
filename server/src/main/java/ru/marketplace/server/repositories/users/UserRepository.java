package ru.marketplace.server.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.users.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByIdAndEmail(Long id, String email);
}
