package ru.marketplace.server.repositories.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.cart.Cart;
import ru.marketplace.server.entities.cart.CartItem;
import ru.marketplace.server.entities.products.Product;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    void deleteByIdAndCart(Long id, Cart cart);
}
