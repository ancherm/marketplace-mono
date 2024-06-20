package ru.marketplace.server.controllers.cart;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.marketplace.server.entities.cart.Cart;
import ru.marketplace.server.entities.delivery.DeliveryPoint;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.users.UserRepository;
import ru.marketplace.server.services.cart.CartService;
import ru.marketplace.server.services.delivery.DeliveryPointService;
import ru.marketplace.server.services.products.ProductService;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserRepository userRepository;
    private final DeliveryPointService deliveryPointService;

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Cart cart = cartService.getCartByUser(user);

        if (!cart.isTimerStarted() || cart.getItems().isEmpty()) {
            model.addAttribute("timeLeft", 0);
        } else {
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(now, cart.getCreatedCart().plusMinutes(cartService.getTimeMinutesLeft()));
            long secondsLeft = duration.getSeconds();
            model.addAttribute("timeLeft", secondsLeft);
        }

        BigDecimal totalPrice = cartService.calculateTotalPrice(cart);
        model.addAttribute("totalPrice", totalPrice);

        List<DeliveryPoint> deliveryPoints = deliveryPointService.getAllDeliveryPoints();
        model.addAttribute("deliveryPoints", deliveryPoints);

        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam int quantity, Principal principal, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Optional<Product> productOptional = productService.getProductById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            if (quantity > 0 && quantity <= product.getQuantity()) {
                cartService.addToCart(user, product, quantity);
            }
        }
        return "redirect:/catalog/" + productId;
    }

    @PostMapping("/cart/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId, Principal principal, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        cartService.removeFromCart(user, itemId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkoutCart(@RequestParam Long deliveryPointId, Principal principal, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        DeliveryPoint deliveryPoint = deliveryPointService.findById(deliveryPointId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery point ID"));

        cartService.checkoutCart(user, deliveryPoint);
        return "redirect:/catalog";
    }
}
