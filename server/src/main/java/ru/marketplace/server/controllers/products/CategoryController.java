package ru.marketplace.server.controllers.products;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marketplace.server.entities.products.Category;
import ru.marketplace.server.services.products.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Category>> getAll() {
        System.out.println(categoryService.findAll());
        return ResponseEntity.ok(categoryService.findAll());
    }
}
