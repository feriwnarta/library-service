package com.feriwinarta.library.controller;

import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.entity.Unit;
import com.feriwinarta.library.repository.CategoryRepository;
import com.feriwinarta.library.repository.RawIngredientRepository;
import com.feriwinarta.library.repository.UnitRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RawIngredientControllerTest extends BaseTestContainerPostgres {
    @Autowired
    private RawIngredientRepository rawIngredientRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @BeforeEach
    void setUp() {
        super.setUp();
        rawIngredientRepository.deleteAll();
        unitRepository.save(Unit.builder()
                .code("01")
                .name("KG")
                .build());
        categoryRepository.save(Category.builder()
                .code("01")
                .name("DAGING")
                .build());

    }

    @Test
    @SneakyThrows
    void givenRawIngredient_whenSaveRawIngredient_thenHappyPath() {
        MultipartFile image = new MockMultipartFile("image", "test-image.jpg", "image/jpeg", "test image content".getBytes());

        Unit unit = unitRepository.findAll().stream().findFirst().orElseThrow();
        Category category = categoryRepository.findAll().stream().findFirst().orElseThrow();

        Response response = RestAssured.given()
                .request()
                .multiPart("image", image.getOriginalFilename(), image.getInputStream(), image.getContentType())
                .multiPart("code", "CODE")
                .multiPart("name", "Tepung")
                .multiPart("branchId", "1")
                .multiPart("categoryId", category.getId())
                .multiPart("unitId", unit.getId())
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/ingredients/raw-ingredients")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .response();

        Assertions.assertNotNull(response);

        // Validasi respons
        response.then().body("data.name", equalTo("Tepung"));
        response.then().body("data.code", equalTo("CODE"));
    }
}
