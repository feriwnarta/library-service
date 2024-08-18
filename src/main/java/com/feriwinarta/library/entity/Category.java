package com.feriwinarta.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Data
@Getter
@Setter
@Table(name = "categories")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {
    @Column(length = 100, nullable = false, unique = true)
    private String code;
    @Column(length = 255, nullable = false)
    private String name;

}
