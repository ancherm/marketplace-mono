package ru.marketplace.server.services.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.cart.Purchase;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.cart.PurchaseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Long, Integer> getSalesDataBySeller(User seller) {
        return purchaseRepository.findAllByProduct_Seller(seller).stream()
                .collect(Collectors.toMap(
                        purchase -> purchase.getProduct().getId(),
                        Purchase::getQuantity,
                        Integer::sum
                ));
    }

}
