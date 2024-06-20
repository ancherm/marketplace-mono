package ru.marketplace.server.entities.delivery;

import jakarta.persistence.*;
import lombok.*;
import ru.marketplace.server.entities.cart.Purchase;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_points")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "deliveryPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases = new ArrayList<>();
}
