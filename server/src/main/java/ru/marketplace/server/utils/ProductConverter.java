package ru.marketplace.server.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.marketplace.server.dto.ProductDTO;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.users.User;
import ru.marketplace.server.services.products.CategoryService;
import ru.marketplace.server.services.users.UserService;

@Component
@AllArgsConstructor
public class ProductConverter {

    private final CategoryService categoryService;
    private final UserService userService;

    public ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .sellerId(product.getSeller().getId())
                .sellerUsername(product.getSeller().getUsername())
                .build();
    }

    public Product convertToEntity(ProductDTO productDTO) {
        Product product = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .build();


        Category category = categoryService.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + productDTO.getCategoryId()));
        product.setCategory(category);

        User seller = userService.findById(productDTO.getSellerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid seller ID: " + productDTO.getSellerId()));
        product.setSeller(seller);


        return product;
    }
}
