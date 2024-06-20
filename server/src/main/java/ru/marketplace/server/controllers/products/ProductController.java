package ru.marketplace.server.controllers.products;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.services.products.CategoryService;
import ru.marketplace.server.services.products.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

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
    public String getProductDetails(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "products/product-details";
        } else {
            return "redirect:/catalog";
        }
    }
}
