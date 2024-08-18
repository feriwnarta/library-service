package com.feriwinarta.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "semi_finish_recipes")
@EqualsAndHashCode(callSuper = false)
public class SemiFinishRecipe extends BaseEntity {

    private Long outcome;
    @OneToMany(mappedBy = "recipe")
    private List<SemiFinishRecipeIngredient> recipes;
}
