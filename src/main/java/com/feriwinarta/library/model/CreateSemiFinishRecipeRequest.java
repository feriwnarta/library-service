package com.feriwinarta.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Data
@Getter
public class CreateSemiFinishRecipeRequest {
    @NotNull
    @Size(min = 1)
    private Long outcome;
    @NotEmpty
    @NotBlank
    private String semiFinishIngredientId;
    @NotNull
    private List<Map<String, Long>> ingredients;
}
