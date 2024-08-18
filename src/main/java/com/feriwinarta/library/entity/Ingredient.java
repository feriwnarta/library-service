package com.feriwinarta.library.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ingredients")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Ingredient extends BaseEntity {
    @Column(nullable = false, unique = true, length = 100)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
    private Integer stockAlert;
}
