package dev.softice.slice.category.service;

import java.util.List;

import dev.softice.slice.category.domain.Category;
import dev.softice.slice.category.repository.CategoryRepository;
import dev.softice.slice.common.IdGenerator;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final IdGenerator idGenerator;

    public CategoryService(CategoryRepository categoryRepository, IdGenerator idGenerator) {
        this.categoryRepository = categoryRepository;
        this.idGenerator = idGenerator;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category categoryToCreate) {
        categoryToCreate.setId(idGenerator.generate());
        return categoryRepository.create(categoryToCreate);
    }

    public Category findById(String id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
