package ru.marketplace.server.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.marketplace.server.services.cart.CartService;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class SchedulingConfig {

    private final CartService cartService;

    @Scheduled(fixedRate = 1000)
    public void clearExpiredCarts() {
        cartService.clearExpiredCarts();
    }
}
