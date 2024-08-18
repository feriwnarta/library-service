package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CategoryResponse;
import com.feriwinarta.library.model.CreateCategoryRequest;

public interface CategoryService {
    CategoryResponse create(CreateCategoryRequest request);
}
