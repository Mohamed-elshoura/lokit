package com.elshoura.lokit.utils.mapper;


import com.elshoura.lokit.models.dto.request.CategoryRequest;
import com.elshoura.lokit.models.dto.response.CategoryResponse;
import com.elshoura.lokit.models.entitys.Category;

public class CategoryMapper {

    private CategoryMapper() {
        throw new IllegalStateException(" Utility class can't be instantiated");
    }

  public static Category toCategory(CategoryRequest categoryRequest) {

     return Category.builder()
                .name(categoryRequest.name().trim())
                .build();
  }

  public static CategoryResponse mapToCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
  }
}
