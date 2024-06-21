package ru.marketplace.server.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.repositories.users.UserRepository;
import ru.marketplace.server.services.users.UserService;

@Component
@AllArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;

    public User isExistUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
