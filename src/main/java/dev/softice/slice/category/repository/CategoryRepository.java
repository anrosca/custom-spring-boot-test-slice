package dev.softice.slice.category.repository;

import java.util.List;
import java.util.Optional;

import dev.softice.slice.category.domain.Category;

public interface CategoryRepository {
    List<Category> findAll();

    Category create(Category categoryToCreate);

    Optional<Category> findById(String id);
}
