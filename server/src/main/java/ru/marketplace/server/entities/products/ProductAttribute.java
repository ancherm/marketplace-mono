package ru.marketplace.server.entities.products;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "product_attributes")
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}