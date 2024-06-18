package ru.marketplace.server.controllers.products;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.services.products.ProductService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class PhotoController {

    private final ProductService productService;

    @GetMapping("/images/{productId}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        Optional<Product> productOptional = productService.getProductById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            byte[] imageBytes = product.getPhoto();

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
