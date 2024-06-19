package ru.marketplace.server.entities.seller;

import jakarta.persistence.*;
import lombok.*;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sellers")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;
    private String email;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
}
