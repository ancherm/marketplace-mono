package ru.marketplace.server.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.products.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteAll();
}
