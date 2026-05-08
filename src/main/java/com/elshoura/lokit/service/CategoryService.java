package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.CategoryRequest;
import com.elshoura.lokit.models.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse addCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(Long id,CategoryRequest categoryRequest);

    CategoryResponse getCategory(Long id);

    List<CategoryResponse> getAllCategories();

    void deleteCategory(Long  id);

}
