package ru.marketplace.server.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.products.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
//    Optional<Category> findById(Long id);
    void deleteById(Long id);
}
