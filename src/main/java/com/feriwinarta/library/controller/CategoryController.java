package com.feriwinarta.library.controller;

import com.feriwinarta.library.model.CategoryResponse;
import com.feriwinarta.library.model.CreateCategoryRequest;
import com.feriwinarta.library.model.WebResponse;
import com.feriwinarta.library.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(value = "/categories",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<CategoryResponse> create(
            @RequestBody @Valid CreateCategoryRequest request
    ) {
        return WebResponse.<CategoryResponse>builder().data(categoryService.create(request)).build();
    }
}
