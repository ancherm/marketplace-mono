package ru.marketplace.server.controllers.products;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.services.products.ProductService;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("/catalog")
    public String getProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/catalog";
    }
}
