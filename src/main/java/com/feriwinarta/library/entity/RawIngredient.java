package com.feriwinarta.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "raw_ingredients")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class RawIngredient extends Ingredient {
    @Column(nullable = true)
    private String thumbnail;
}
