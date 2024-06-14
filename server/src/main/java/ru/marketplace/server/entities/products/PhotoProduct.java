package ru.marketplace.server.entities.products;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "product_photos")
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
