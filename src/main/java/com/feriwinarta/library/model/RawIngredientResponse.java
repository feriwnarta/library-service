package com.feriwinarta.library.model;

import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.entity.Unit;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Data
@Getter
@Setter
public class RawIngredientResponse {
    private String id;
    private String code;
    private String name;
    private Category category;
    private Unit unit;
    private Integer stockAlert;
    private BigDecimal initialStock;
    private BigDecimal averageCost;
    // TODO: tambahkan placements
}
