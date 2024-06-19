package ru.marketplace.server.repositories.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
