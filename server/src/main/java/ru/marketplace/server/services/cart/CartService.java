package ru.marketplace.server.services.cart;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.cart.Cart;
import ru.marketplace.server.entities.cart.CartItem;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.cart.CartItemRepository;
import ru.marketplace.server.repositories.cart.CartRepository;
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
    public void checkoutCart(User user) {
        Cart cart = getCartByUser(user);
        cart.getItems().clear();
        cart.setTimerStarted(false);
        cartRepository.save(cart);
    }

    private Cart createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreatedCart(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    @Transactional
    public void clearExpiredCarts() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(1);
        List<Cart> expiredCarts = cartRepository.findAllByCreatedCartBefore(expirationTime);

        for (Cart cart : expiredCarts) {
            List<CartItem> cartItems = cart.getItems();
            for (CartItem cartItem: cartItems) {
                Product product = cartItem.getProduct();
                product.setQuantity(product.getQuantity() + cartItem.getQuantity());
                productRepository.save(product);
            }
            cart.getItems().clear();
            cart.setTimerStarted(false);
            cartRepository.save(cart);
        }
    }

    public BigDecimal calculateTotalPrice(Cart cart) {
        return cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
