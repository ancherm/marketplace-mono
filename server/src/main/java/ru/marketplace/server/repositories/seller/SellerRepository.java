package ru.marketplace.server.repositories.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.seller.Seller;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
