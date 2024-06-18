package ru.marketplace.server.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.products.ProductAttribute;
import ru.marketplace.server.entities.seller.Seller;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductFormDTO {

    private String name;
    private Category category;
    private BigDecimal price;
    private Integer quantity;
    private Seller seller;
    private String description;
    private MultipartFile[] photos;
    private List<ProductAttribute> attributes;

}
