package ru.marketplace.server.controllers.users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.marketplace.server.entities.cart.Purchase;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.users.UserRepository;
import ru.marketplace.server.services.cart.PurchaseService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PurchaseService purchaseService;

    @GetMapping("/user/profile")
    public String getProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            List<Purchase> purchases = purchaseService.findByUser(user);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            model.addAttribute("user", user);
            model.addAttribute("purchases", purchases.stream().map(purchase -> {
                purchase.setPurchaseDateFormatted(purchase.getPurchaseDate().format(formatter));
                return purchase;
            }).collect(Collectors.toList()));
            return "users/profile";
        } else {
            return "redirect:/login";
        }
    }
}
