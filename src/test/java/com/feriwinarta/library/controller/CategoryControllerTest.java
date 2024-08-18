package com.feriwinarta.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.model.CreateCategoryRequest;
import com.feriwinarta.library.repository.CategoryRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends BaseTestContainerPostgres {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        super.setUp();
        categoryRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void givenCategory_whenSaveCategory_thenReturnHappyPath() {
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .code("CATEGORY01")
                .name("DAGING")
                .build();


        RestAssured.given()
                .request()
                .body(mapper.writeValueAsString(request))
                .contentType(ContentType.JSON)
                .when()
                .post("/ingredients/categories")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("data.code", Matchers.equalTo("CATEGORY01"))
                .body("data.name", Matchers.equalTo("DAGING"));
    }

    @Test
    @SneakyThrows
    void givenCategory_whenSaveCategoryDuplicateCode_thenReturnError() {
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .code("CATEGORY01")
                .name("DAGING")
                .build();

        categoryRepository.save(Category.builder()
                .code("CATEGORY01")
                .name("MIE")
                .build());

        RestAssured.given()
                .request()
                .body(mapper.writeValueAsString(request))
                .contentType(ContentType.JSON)
                .when()
                .post("/ingredients/categories")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body("error", Matchers.equalTo("The code already exists"));

    }

    @Test
    @SneakyThrows
    void givenCategoryWithNullCode_whenBeanValidationProcess_thenReturnError() {
        CreateCategoryRequest request = CreateCategoryRequest.builder()
                .name("DAGING")
                .build();

        RestAssured.given()
                .request()
                .body(mapper.writeValueAsString(request))
                .contentType(ContentType.JSON)
                .when()
                .post("/ingredients/categories")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // .body("errors", Matchers.equalTo(List.of("code must not be empty", "code must not be blank"))); // disable cause position error message dynamically changes

    }

}
