package ru.marketplace.server.entities.cart;

import jakarta.persistence.*;
import lombok.*;
import ru.marketplace.server.entities.delivery.DeliveryPoint;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Transient
    private String purchaseDateFormatted;

    @ManyToOne
    @JoinColumn(name = "delivery_point_id", nullable = false)
    private DeliveryPoint deliveryPoint;
}
