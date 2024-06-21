package ru.marketplace.server.controllers.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.services.products.CategoryService;
import ru.marketplace.server.services.products.ProductService;
import ru.marketplace.server.services.products.ReviewService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("newCategory", new Category());
        return "admin/categories";
    }

    @PostMapping("/admin/categories")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/catalog")
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
        return "admin/catalog";
    }

    @GetMapping("/admin/catalog/{id}")
    public String getProductDetails(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);

            return "admin/product-details";
        } else {
            return "redirect:/admin/catalog";
        }

    }

    @PostMapping("/admin/catalog/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/catalog";
    }

    @PostMapping("/admin/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long id, @RequestParam("productId") Long productId) {
        reviewService.deleteById(id);
        return "redirect:/admin/catalog/" + productId;
    }

    @PostMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

}
