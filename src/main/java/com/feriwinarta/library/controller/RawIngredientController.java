package com.feriwinarta.library.controller;

import com.feriwinarta.library.model.CreateRawIngredientRequest;
import com.feriwinarta.library.model.CreateRawIngredientResponse;
import com.feriwinarta.library.model.WebResponse;
import com.feriwinarta.library.service.RawIngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class RawIngredientController {
    private final RawIngredientService rawIngredientService;

    @PostMapping("ingredients/raw-ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<CreateRawIngredientResponse> create(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @ModelAttribute @Valid CreateRawIngredientRequest request
    ) {

        return WebResponse.<CreateRawIngredientResponse>builder()
                .data(rawIngredientService.create(image, request))
                .build();
    }
}
