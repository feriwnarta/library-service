package com.feriwinarta.library.service.impl;

import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.model.CategoryResponse;
import com.feriwinarta.library.model.CreateCategoryRequest;
import com.feriwinarta.library.repository.CategoryRepository;
import com.feriwinarta.library.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryResponse create(CreateCategoryRequest request) {
        Category entity = new Category();
        entity.setName(request.getName());
        entity.setCode(request.getCode());

        Category category = categoryRepository.save(entity);

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .code(category.getCode())
                .build();
    }
}
