package com.feriwinarta.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@Builder
public class CreateRawIngredientRequest {
    @NotBlank
    @NotEmpty
    private String code;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String branchId;
    @NotBlank
    @NotEmpty
    private String categoryId;
    @NotBlank
    @NotEmpty
    private String unitId;
    private Integer stockAlert;
    private BigDecimal initialStock;
    private BigDecimal averageCost;
}