package ru.marketplace.server.services.cart;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.cart.Cart;
import ru.marketplace.server.entities.cart.CartItem;
import ru.marketplace.server.entities.cart.Purchase;
import ru.marketplace.server.entities.delivery.DeliveryPoint;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.cart.CartItemRepository;
import ru.marketplace.server.repositories.cart.CartRepository;
import ru.marketplace.server.repositories.cart.PurchaseRepository;
import ru.marketplace.server.repositories.products.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    private final int timeMinutesLeft = 2;

    public int getTimeMinutesLeft() {
        return timeMinutesLeft;
    }

    @Transactional
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> createCartForUser(user));
    }

    @Transactional
    public void addToCart(User user, Product product, int quantity) {
        Cart cart = getCartByUser(user);
        Optional<CartItem> cartItemOptional = cartItemRepository.findByCartAndProduct(cart, product);

        CartItem cartItem;
        if (cartItemOptional.isPresent()) {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
            cartItemRepository.save(cartItem);

            if (!cart.isTimerStarted()) {
                cart.setTimerStarted(true);
                cart.setCreatedCart(LocalDateTime.now());
            }
        }
        cartRepository.save(cart);
    }

    @Transactional
    public void removeFromCart(User user, Long itemId) {
        Cart cart = getCartByUser(user);
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));

        Product product = cartItem.getProduct();
        product.setQuantity(product.getQuantity() + cartItem.getQuantity());
        productRepository.save(product);

        cartItemRepository.delete(cartItem);

        cart.getItems().remove(cartItem);
        if (cart.getItems().isEmpty()) {
            cart.setTimerStarted(false);
        }
        cartRepository.save(cart);
    }

    @Transactional
    public void checkoutCart(User user, DeliveryPoint deliveryPoint) {
        Cart cart = getCartByUser(user);

        cart.getItems().forEach(item -> {
            Purchase purchase = new Purchase();
            purchase.setUser(user);
            purchase.setProduct(item.getProduct());
            purchase.setQuantity(item.getQuantity());
            purchase.setTotalPrice(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            purchase.setPurchaseDate(LocalDateTime.now());
            purchase.setDeliveryPoint(deliveryPoint);
            purchaseRepository.save(purchase);
        });

        cart.getItems().clear();
        cart.setTimerStarted(false);
        cartRepository.save(cart);
    }


    private Cart createCartForUser(User user) {
        Cart cart = Cart.builder()
                .user(user)
                .createdCart(LocalDateTime.now())
                .build();

        return cartRepository.save(cart);
    }

    @Transactional
    public void clearExpiredCarts() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(timeMinutesLeft);
        List<Cart> expiredCarts = cartRepository.findAllByCreatedCartBefore(expirationTime);

        expiredCarts.forEach(cart -> {
            cart.getItems().forEach(cartItem -> {
                Product product = cartItem.getProduct();
                product.setQuantity(product.getQuantity() + cartItem.getQuantity());
                productRepository.save(product);
            });
            cart.getItems().clear();
            cart.setTimerStarted(false);
            cartRepository.save(cart);
        });
    }


    public BigDecimal calculateTotalPrice(Cart cart) {
        return cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
