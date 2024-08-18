package com.feriwinarta.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@Getter
@Setter
public class CreateSemiFinishRequest {
    @NotBlank
    @NotEmpty
    private String code;
    @NotBlank
    @NotEmpty
    private String name;
    private String categoryId;
    @NotBlank
    @NotEmpty
    private String unitId;
    private Integer stockAlert;
}
