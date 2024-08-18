package com.feriwinarta.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUnitRequest {
    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String code;
}
