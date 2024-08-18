package com.feriwinarta.library.controller;

import com.feriwinarta.library.model.CreateUnitRequest;
import com.feriwinarta.library.model.CreateUnitResponse;
import com.feriwinarta.library.model.WebResponse;
import com.feriwinarta.library.service.UnitService;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UnitController {

    @Setter(onMethod = @__({@Autowired}))
    private UnitService unitService;

    @PostMapping(value = "ingredients/units",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<CreateUnitResponse> create(
            @RequestBody @Valid CreateUnitRequest request
    ) {
        return WebResponse.<CreateUnitResponse>builder()
                .data(unitService.create(request))
                .build();
    }

    @GetMapping(
            path = "/units/{unitId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<CreateUnitResponse> get(@PathVariable("unitId") String unitId) {
        return WebResponse.<CreateUnitResponse>builder()
                .data(unitService.get(unitId))
                .build();
    }
}
