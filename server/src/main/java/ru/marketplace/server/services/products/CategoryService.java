package ru.marketplace.server.services.products;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.repositories.products.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findByName(String name) {
        return Optional.of(categoryRepository.findByName(name)).orElseThrow();
    }

}
