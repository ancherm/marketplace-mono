package ru.marketplace.server.controllers.users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.users.UserRepository;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "users/registration";
    }

    @PostMapping("/registration")
    public String registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username is not found"));
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return "redirect:/catalog";
        }

        model.addAttribute("error", "Error of username or password");
        return "users/login";

    }

}
