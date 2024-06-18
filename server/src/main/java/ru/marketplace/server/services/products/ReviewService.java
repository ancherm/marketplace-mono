package ru.marketplace.server.services.products;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.products.ProductReview;
import ru.marketplace.server.repositories.products.ProductReviewRepository;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ProductReviewRepository productReviewRepository;

    public void save(ProductReview review) {
        productReviewRepository.save(review);
    }
}
