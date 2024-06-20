package ru.marketplace.server.controllers.products;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.server.entities.cart.Purchase;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.products.ProductReview;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.services.cart.PurchaseService;
import ru.marketplace.server.services.products.CategoryService;
import ru.marketplace.server.services.products.ProductService;
import ru.marketplace.server.services.products.ReviewService;
import ru.marketplace.server.services.users.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final PurchaseService purchaseService;
    private final UserService userService;

    @GetMapping("/catalog")
    public String getProducts(@RequestParam(required = false) Long categoryId,
                              @RequestParam(required = false) String search,
                              Model model) {
        List<Product> products;
        if (categoryId != null) {
            products = productService.findByCategory(categoryId);
        } else if (search != null && !search.isEmpty()) {
            products = productService.searchByName(search);
        } else {
            products = productService.findAll();
        }

        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "products/catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getProductDetails(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);

            String username = authentication.getName();
            User user = userService.findByUsername(username).orElse(null);
            boolean canReview = purchaseService.userHasPurchasedProduct(user, product);
            model.addAttribute("canReview", canReview);

            return "products/product-details";
        } else {
            return "redirect:/catalog";
        }

    }

    @PostMapping("/catalog/{id}/review")
    public String addReview(@PathVariable Long id, @RequestParam String reviewerName, @RequestParam String content, @RequestParam int rating, Authentication authentication) {
        Optional<Product> productOptional = productService.getProductById(id);
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElse(null);

        if (productOptional.isPresent() && user != null && purchaseService.userHasPurchasedProduct(user, productOptional.get())) {
            Product product = productOptional.get();
            ProductReview review = ProductReview.builder()
                    .reviewerName(reviewerName)
                    .reviewText(content)
                    .rating(rating)
                    .reviewDate(LocalDateTime.now())
                    .product(product)
                    .build();
            reviewService.save(review);
        }

        return "redirect:/catalog/" + id;
    }
}
