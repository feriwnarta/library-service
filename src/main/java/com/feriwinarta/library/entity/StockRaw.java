package com.feriwinarta.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Entity
@Audited
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stock_raws")
@EqualsAndHashCode(callSuper = false)
public class StockRaw extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "raw_ingredient_id")
    private RawIngredient rawIngredient;
    @Column(name = "branch_id")
    private String branch;
    private BigDecimal incomingQty;
    private BigDecimal incomingValue;
    private BigDecimal priceDiff;
    private BigDecimal inventoryValue;
    private BigDecimal qtyOnHand;
    private BigDecimal avgCost;
    private BigDecimal lastCost;
    private BigDecimal minimumCost;
}
