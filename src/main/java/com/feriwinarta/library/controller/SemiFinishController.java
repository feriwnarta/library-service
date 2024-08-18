package com.feriwinarta.library.controller;

import com.feriwinarta.library.model.CreateSemiFinishRequest;
import com.feriwinarta.library.model.CreateSemiFinishResponse;
import com.feriwinarta.library.model.WebResponse;
import com.feriwinarta.library.service.SemiFinishService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class SemiFinishController {
    private final SemiFinishService semiFinishService;

    @PostMapping(
            value = "ingredients/semi-finish-ingredients",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<CreateSemiFinishResponse> create(
            @RequestParam("image") @Nullable MultipartFile image,
            @ModelAttribute @Valid CreateSemiFinishRequest request
    ) {
        return WebResponse.<CreateSemiFinishResponse>builder()
                .data(semiFinishService.create(image, request))
                .build();
    }

}
