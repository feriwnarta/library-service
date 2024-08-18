package com.feriwinarta.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "semi_finish_ingredients")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class SemiFinishIngredient extends Ingredient {
    @ManyToOne
    @JoinColumn(nullable = true)
    private SemiFinishRecipe semiFinishRecipe;
}
