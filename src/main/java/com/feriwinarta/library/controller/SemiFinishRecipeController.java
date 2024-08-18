package com.feriwinarta.library.controller;

import com.feriwinarta.library.model.CreateSemiFinishRecipeRequest;
import com.feriwinarta.library.model.SemiFinishRecipeResponse;
import com.feriwinarta.library.model.WebResponse;
import com.feriwinarta.library.service.SemiFinishRecipeService;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SemiFinishRecipeController {
    @Setter(onMethod = @__({@Autowired}))
    private SemiFinishRecipeService semiFinishRecipeService;

    @PostMapping(
            value = "/semi-finish-ingredients/recipes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<SemiFinishRecipeResponse> create(
            @Valid CreateSemiFinishRecipeRequest request
    ) {
        return WebResponse.<SemiFinishRecipeResponse>builder()
                .data(semiFinishRecipeService.create(request))
                .build();
    }

}
