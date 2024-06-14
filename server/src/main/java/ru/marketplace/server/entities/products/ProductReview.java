package ru.marketplace.server.entities.products;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "product_reviews")
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewerName;

    private String reviewText;

    @Column(nullable = false)
    private int rating;

    private LocalDateTime reviewDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
