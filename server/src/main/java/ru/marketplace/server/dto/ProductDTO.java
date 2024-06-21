package ru.marketplace.server.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.marketplace.server.entities.products.Category;

import java.math.BigDecimal;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long sellerId;
    private String sellerUsername;
    private Integer quantity;
    private Long categoryId;
    private MultipartFile photo;
}
