package ru.marketplace.server.controllers.seller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.users.UserRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SellerAuthController {

    private final UserRepository userRepository;

    @GetMapping("/seller/profile")
    public String sellerProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        model.addAttribute("user", user);
        return "seller/profile";
    }
}
