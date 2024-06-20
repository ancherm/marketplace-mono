package ru.marketplace.server.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.products.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameContainingIgnoreCase(String name);

//    List<Product> findByOwnerId(Long ownerId);
//    List<Product> findByBuyersId(Long userId);
    void deleteAll();
}
