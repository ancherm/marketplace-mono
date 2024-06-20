package ru.marketplace.server.controllers.seller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.services.products.CategoryService;
import ru.marketplace.server.services.products.ProductService;
import ru.marketplace.server.services.users.UserService;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class ProductSellerController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/seller/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "seller/add-product";
    }

    @PostMapping("/seller/product/add")
    public String addProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, Authentication authentication) {
        String username = authentication.getName();
        User seller = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        product.setSeller(seller);

        try {
            if (!file.isEmpty()) {
                product.setPhoto(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.save(product);
        return "redirect:/seller/profile";
    }

    @GetMapping("/seller/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "seller/edit-product";
    }

    @PostMapping("/seller/product/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, @RequestParam("file") MultipartFile file) {
        Product existingProduct = productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setCategory(product.getCategory());

        try {
            if (!file.isEmpty()) {
                existingProduct.setPhoto(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.save(existingProduct);
        return "redirect:/seller/profile";
    }
}
