package ru.marketplace.server.controllers.users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.marketplace.server.entities.cart.Purchase;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.services.cart.PurchaseService;
import ru.marketplace.server.utils.UserUtil;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserUtil userUtil;
    private final PurchaseService purchaseService;

    @GetMapping("/user/profile")
    public String getProfile(Model model, Authentication authentication) {
        User user = userUtil.isExistUser(authentication.getName());


        if (user != null) {
            List<Purchase> purchases = purchaseService.findByUser(user);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            model.addAttribute("user", user);
            model.addAttribute("purchases", purchases.stream()
                    .peek(purchase -> purchase.setPurchaseDateFormatted(purchase.getPurchaseDate().format(formatter)))
                    .toList());
            return "users/profile";
        }

        return "redirect:/login";
    }

}
