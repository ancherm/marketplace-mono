package ru.marketplace.server.repositories.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.delivery.DeliveryPoint;

public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, Long> {

}
