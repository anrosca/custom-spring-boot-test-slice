package dev.softice.slice.category.web;

import dev.softice.slice.category.domain.Category;

public record CreateCategoryRequest(String name) {

    public Category toCategory() {
        return Category.builder()
                    .name(name)
                    .build();
    }
}
