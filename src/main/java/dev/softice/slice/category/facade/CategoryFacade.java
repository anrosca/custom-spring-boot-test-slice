package dev.softice.slice.category.facade;

import java.util.List;

import dev.softice.slice.category.domain.Category;
import dev.softice.slice.category.service.CategoryService;
import dev.softice.slice.category.web.CategoryResponse;
import dev.softice.slice.category.web.CreateCategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryFacade {
    private final CategoryService categoryService;

    public CategoryFacade(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<CategoryResponse> findAll() {
        return categoryService.findAll()
                              .stream()
                              .map(CategoryResponse::from)
                              .toList();
    }

    public CategoryResponse findById(String id) {
        Category category = categoryService.findById(id);
        return CategoryResponse.from(category);
    }

    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        Category categoryToCreate = createCategoryRequest.toCategory();
        Category createdCategory = categoryService.create(categoryToCreate);
        return CategoryResponse.from(createdCategory);
    }
}
