package ru.marketplace.server.repositories.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.cart.Cart;
import ru.marketplace.server.entities.users.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

    List<Cart> findAllByCreatedCartBefore(LocalDateTime expirationTime);
}
