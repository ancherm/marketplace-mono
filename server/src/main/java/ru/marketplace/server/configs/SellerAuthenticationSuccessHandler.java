package ru.marketplace.server.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
public class SellerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_SELLER")) {
                try {
                    response.sendRedirect("/seller/profile");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (authority.getAuthority().equals("ROLE_BUYER")) {
                try {
                    response.sendRedirect("/catalog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    response.sendRedirect("/admin/catalog");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
