package ru.marketplace.server.services.delivery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.delivery.DeliveryPoint;
import ru.marketplace.server.repositories.delivery.DeliveryPointRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryPointService {

    private final DeliveryPointRepository deliveryPointRepository;

    public List<DeliveryPoint> getAllDeliveryPoints() {
        return deliveryPointRepository.findAll();
    }

    public Optional<DeliveryPoint> findById(Long deliveryPointId) {
        return deliveryPointRepository.findById(deliveryPointId);
    }
}
