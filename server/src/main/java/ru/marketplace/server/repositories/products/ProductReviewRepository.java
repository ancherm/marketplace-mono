package ru.marketplace.server.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.products.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    void deleteById(Long id);
}
