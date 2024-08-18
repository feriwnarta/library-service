package com.feriwinarta.library.controller;

import com.feriwinarta.library.entity.Unit;
import com.feriwinarta.library.model.*;
import com.feriwinarta.library.service.CategoryService;
import com.feriwinarta.library.service.UnitService;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UnitController {

    @Setter(onMethod = @__({@Autowired}))
    private UnitService unitService;

    @PostMapping(value = "/units",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<UnitResponse> create(
            @RequestBody @Valid CreateUnitRequest request
    ) {
        return WebResponse.<UnitResponse>builder()
                .data(unitService.create(request))
                .build();
    }

    @GetMapping(
            path = "/units/{unitId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<UnitResponse> get(@PathVariable("unitId") String unitId) {
        return WebResponse.<UnitResponse>builder()
                .data(unitService.get(unitId))
                .build();
    }
}
