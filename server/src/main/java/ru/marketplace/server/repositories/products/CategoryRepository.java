package ru.marketplace.server.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.server.entities.products.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
