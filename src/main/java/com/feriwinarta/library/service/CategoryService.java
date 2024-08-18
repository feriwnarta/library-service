package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateCategoryRequest;
import com.feriwinarta.library.model.CreateCategoryResponse;

public interface CategoryService {
    CreateCategoryResponse create(CreateCategoryRequest request);
}
