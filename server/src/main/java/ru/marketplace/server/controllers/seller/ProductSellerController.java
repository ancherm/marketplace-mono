package ru.marketplace.server.controllers.seller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.marketplace.server.dto.ProductDTO;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.services.products.CategoryService;
import ru.marketplace.server.services.products.ProductService;
import ru.marketplace.server.services.users.UserService;
import ru.marketplace.server.utils.ProductConverter;
import ru.marketplace.server.utils.UserUtil;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class ProductSellerController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductConverter productConverter;
    private final UserUtil userUtil;

    @GetMapping("/seller/product/add")
    public String addProduct(Model model, Authentication authentication) {
        User seller = userUtil.isExistUser(authentication.getName());

        model.addAttribute("productDTO", ProductDTO.builder().sellerId(seller.getId()).build());
        model.addAttribute("categories", categoryService.findAll());
        return "seller/add-product";
    }

    @PostMapping("/seller/product/add")
    public String addProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
        Product product = productConverter.convertToEntity(productDTO);

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
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "seller/edit-product";
    }

    @PostMapping("/seller/product/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, @RequestParam("file") MultipartFile file) {
        Product p = productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setQuantity(product.getQuantity());
        p.setCategory(product.getCategory());

        try {
            if (!file.isEmpty()) {
                p.setPhoto(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.save(p);
        return "redirect:/seller/profile";
    }
}
