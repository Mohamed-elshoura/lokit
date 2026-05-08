package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.AlreadyExistException;
import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.dto.request.CategoryRequest;
import com.elshoura.lokit.models.dto.response.CategoryResponse;
import com.elshoura.lokit.models.entitys.Category;
import com.elshoura.lokit.repository.CategoryRepository;
import com.elshoura.lokit.utils.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.elshoura.lokit.utils.mapper.CategoryMapper.mapToCategory;
import static com.elshoura.lokit.utils.mapper.CategoryMapper.toCategory;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
   public CategoryResponse addCategory(CategoryRequest categoryRequest){

        String name =categoryRequest.name().trim();

        categoryRepository.findByNameIgnoreCase(name).ifPresent(category -> {
            throw new AlreadyExistException("Category already exists");
        });

        Category category = toCategory(categoryRequest);

        Category SavedCategory = categoryRepository.save(category);

        return mapToCategory(SavedCategory);

    }
    @Override
     public CategoryResponse updateCategory(Long id,CategoryRequest categoryRequest){

       Category category= categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found"));

        String name =categoryRequest.name().trim();

          categoryRepository.findByNameIgnoreCase(name)
              .filter(existingCategory -> !existingCategory.getId().equals(id))
                .ifPresent(c-> {throw new AlreadyExistException("category name already exist");
                });

        category.setName(name);
        Category updatedCategory = categoryRepository.save(category);

        return mapToCategory(updatedCategory);
    }
    @Override
    public CategoryResponse getCategory(Long id){

        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Category not found"));

        return mapToCategory(category);

    }
    @Override
    public List<CategoryResponse> getAllCategories(){

       return categoryRepository.findAllByOrderByIdAsc().stream()
               .map(CategoryMapper::mapToCategory).toList();

    }
    @Override
   public void deleteCategory(Long id){

       categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found"));

       categoryRepository.deleteById(id);

   }

}
