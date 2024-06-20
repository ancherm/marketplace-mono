package ru.marketplace.server.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.marketplace.server.entities.products.Category;

import java.math.BigDecimal;

@Data
public class ProductFormDTO {

    private String name;
    private Category category;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    private MultipartFile[] photos;

}
