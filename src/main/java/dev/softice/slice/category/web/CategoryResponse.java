package dev.softice.slice.category.web;

import dev.softice.slice.category.domain.Category;
import lombok.Builder;

@Builder
public record CategoryResponse(String id, String name) {

    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                               .id(category.getId())
                               .name(category.getName())
                               .build();
    }
}
