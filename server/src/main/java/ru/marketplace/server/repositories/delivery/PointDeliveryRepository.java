package ru.marketplace.server.repositories.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.delivery.PointDelivery;

public interface PointDeliveryRepository extends JpaRepository<PointDelivery, Long> {

}
