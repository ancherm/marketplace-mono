package ru.marketplace.server.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.marketplace.server.services.cart.CartService;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    private final CartService cartService;

    public SchedulingConfig(CartService cartService) {
        this.cartService = cartService;
    }

    @Scheduled(fixedRate = 60000) // выполняется каждую минуту
    public void clearExpiredCarts() {
        cartService.clearExpiredCarts();
    }
}
