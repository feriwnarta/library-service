package com.feriwinarta.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StockRawRequest {
    @NotBlank
    @NotEmpty
    private String ingredientId;
    @NotBlank
    @NotEmpty
    private String branchId;
    private BigDecimal incomingQty;
    private BigDecimal avgCost;
    private BigDecimal lastCost;
}
