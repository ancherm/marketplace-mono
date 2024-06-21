package ru.marketplace.server.utils;

import org.springframework.stereotype.Component;
import ru.marketplace.server.dto.CategoryDTO;
import ru.marketplace.server.entities.products.Category;

@Component
public class CategoryConverter {

    public CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }
}
