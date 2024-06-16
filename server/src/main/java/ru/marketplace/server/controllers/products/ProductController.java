package ru.marketplace.server.controllers.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marketplace.server.entities.products.Product;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("TestCallSuccess");
    }
}
