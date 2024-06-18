package ru.marketplace.server.services.products;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.marketplace.server.entities.products.PhotoProduct;
import ru.marketplace.server.entities.products.Product;
import ru.marketplace.server.entities.products.ProductAttribute;
import ru.marketplace.server.repositories.products.ProductAttributeRepository;
import ru.marketplace.server.repositories.products.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProductAttributeRepository productAttributeRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product, MultipartFile file, Map<String, String> attributes) {
        // Сохранение фото
        if (!file.isEmpty()) {
            try {
//                PhotoProduct photoProduct = new PhotoProduct();
//                photoProduct.setPhoto(file.getBytes());
//                photoProduct.setProduct(product);
                product.setPhoto(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Сохранение атрибутов

        attributes.forEach((name, value) -> {
            ProductAttribute attribute = new ProductAttribute();
            attribute.setName(name);
            attribute.setValue(value);
            attribute.setProduct(product);
            product.getAttributes().add(attribute);
        });

        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

}
