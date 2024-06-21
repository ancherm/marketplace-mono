package ru.marketplace.server.controllers.seller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.users.UserRepository;

import lombok.AllArgsConstructor;
import ru.marketplace.server.services.cart.PurchaseService;
import ru.marketplace.server.services.users.UserService;
import ru.marketplace.server.utils.UserUtil;

import java.util.Map;

@Controller
@AllArgsConstructor
public class SellerAuthController {

    private final UserService userService;
    private final UserUtil userUtil;
    private final PurchaseService purchaseService;

    @GetMapping("/seller/profile")
    public String sellerProfile(Model model, Authentication authentication) {
        User user = userUtil.isExistUser(authentication.getName());

        model.addAttribute("user", user);

        Map<Long, Integer> salesData = purchaseService.getSalesDataBySeller(user);
        model.addAttribute("salesData", salesData);

        return "seller/profile";
    }
}
