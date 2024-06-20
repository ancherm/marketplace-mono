package ru.marketplace.server.controllers.seller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.services.products.CategoryService;
import ru.marketplace.server.services.products.ProductService;

import java.util.Map;
import java.util.stream.Collectors;

@Controller("/seller/product")
@AllArgsConstructor
public class ProductSellerController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/seller/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "seller/add-product";
    }

    @PostMapping("/seller/product/add")
    public String addProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file) {
        // Извлечение атрибутов из параметров запроса


        productService.save(product, file);
        return "redirect:/seller/product/add";
    }
}
