package ru.marketplace.server.entities.delivery;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_points")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;
}
