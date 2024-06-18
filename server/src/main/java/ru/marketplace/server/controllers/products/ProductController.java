package ru.marketplace.server.controllers.products;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.products.ProductReview;
import ru.marketplace.server.services.products.ProductService;
import ru.marketplace.server.services.products.ReviewService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    @GetMapping("/catalog")
    public String getProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "products/product-details";
        } else {
            return "redirect:/catalog";
        }
    }

    @PostMapping("/catalog/{id}/review")
    public String addReview(@PathVariable Long id, @RequestParam String reviewerName, @RequestParam String content, @RequestParam int rating) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductReview review = new ProductReview();
            review.setReviewerName(reviewerName);
            review.setReviewText(content);
            review.setRating(rating);
            review.setProduct(product);
            reviewService.save(review);
        }
        return "redirect:/catalog/" + id;
    }
}
