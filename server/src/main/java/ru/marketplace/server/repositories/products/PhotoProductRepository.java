package ru.marketplace.server.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.products.PhotoProduct;

public interface PhotoProductRepository extends JpaRepository<PhotoProduct, Long> {
}
