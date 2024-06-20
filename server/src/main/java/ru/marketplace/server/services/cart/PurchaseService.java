package ru.marketplace.server.services.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.cart.Purchase;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.cart.PurchaseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;


    public boolean userHasPurchasedProduct(User user, Product product) {
        List<Purchase> purchases = purchaseRepository.findByUserAndProduct(user, product);
        return !purchases.isEmpty();
    }

    public List<Purchase> findByUser(User user) {
        return purchaseRepository.findByUser(user);
    }
}
