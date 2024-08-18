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
@Table(name = "units")
@EqualsAndHashCode(callSuper = false)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Unit extends BaseEntity {
    @Column(length = 100, unique = true, nullable = false)
    private String name;
    @Column(length = 255, unique = false, nullable = false)
    private String code;
}
