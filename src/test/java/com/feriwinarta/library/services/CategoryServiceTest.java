package com.feriwinarta.library.services;

import com.feriwinarta.library.model.CategoryResponse;
import com.feriwinarta.library.model.CreateCategoryRequest;
import com.feriwinarta.library.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CategoryServiceTest {
    @MockBean
    private CategoryService categoryService;
    private CreateCategoryRequest createCategoryRequest;

    @BeforeEach
    void setUp() {
        createCategoryRequest = CreateCategoryRequest.builder()
                .code("code")
                .name("name")
                .build();
    }

    @Test
    void givenCategory_whenSaveCategory_thenReturnCategory() {
        Mockito.when(categoryService.create(any(CreateCategoryRequest.class))).thenReturn(CategoryResponse.builder()
                .id("1")
                .code("code")
                .name("name")
                .build());

        CategoryResponse categoryResponse = categoryService.create(createCategoryRequest);

        Mockito.verify(categoryService, Mockito.times(1)).create(any(CreateCategoryRequest.class));

        Assertions.assertNotNull(categoryResponse);
        Assertions.assertEquals(categoryResponse.getName(), createCategoryRequest.getName());
    }

    @Test
    void givenCategory_whenSaveCategoryDuplicateCode_thenReturnError() {
        Mockito.when(categoryService.create(any(CreateCategoryRequest.class)))
                .thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.create(createCategoryRequest);
        });

        Mockito.verify(categoryService, Mockito.times(1)).create(any(CreateCategoryRequest.class));
    }
}
