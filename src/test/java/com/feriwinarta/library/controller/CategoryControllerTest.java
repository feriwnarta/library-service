package com.feriwinarta.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.model.CreateCategoryRequest;
import com.feriwinarta.library.repository.CategoryRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );
    @LocalServerPort
    private Integer port;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ObjectMapper mapper;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
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
                .statusCode(201)
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
                .statusCode(409)
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
                .statusCode(400)
                .body("errors", Matchers.equalTo(List.of("code must not be empty", "code must not be blank  ")));

    }

}
