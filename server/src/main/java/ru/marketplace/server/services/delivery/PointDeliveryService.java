package ru.marketplace.server.services.delivery;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.delivery.PointDelivery;
import ru.marketplace.server.repositories.delivery.PointDeliveryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PointDeliveryService {

    private final PointDeliveryRepository pointDeliveryRepository;

    public List<PointDelivery> getAllDeliveryPoints() {
        return pointDeliveryRepository.findAll();
    }
}
