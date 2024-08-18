package com.feriwinarta.library.model;

import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.entity.Unit;
import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SemiFinishResponse {
    private String id;
    private String code;
    private String name;
    private String image;
    private Category category;
    private Unit unit;
    private Integer stockAlert;
}
