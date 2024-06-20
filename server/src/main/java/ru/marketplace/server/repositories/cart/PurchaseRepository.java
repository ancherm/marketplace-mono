package ru.marketplace.server.repositories.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.cart.Purchase;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUserAndProduct(User user, Product product);

    List<Purchase> findByUser(User user);

    List<Purchase> findAllByProduct_Seller(User seller);
}
