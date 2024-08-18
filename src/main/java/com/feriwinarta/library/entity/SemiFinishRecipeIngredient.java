package com.feriwinarta.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "semi_finish_recipe_ingredients")
@EqualsAndHashCode(callSuper = false)
public class SemiFinishRecipeIngredient extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "semi_finish_recipe_id", nullable = false)
    private SemiFinishRecipe recipe;
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    private Long usageAmount;
}
