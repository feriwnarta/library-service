package com.feriwinarta.library.service.impl;

import com.feriwinarta.library.entity.StockRaw;
import com.feriwinarta.library.service.InventoryValuation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CogsInventoryValuation implements InventoryValuation {

    @Override
    public StockRaw calculateInitialAvgCost(BigDecimal initialStock, BigDecimal initialAvgCost, BigDecimal initialLastCost) {
        BigDecimal defaultZero = BigDecimal.ZERO;

        BigDecimal avgCost = Optional.ofNullable(initialAvgCost).orElse(defaultZero);
        BigDecimal incomingQty = Optional.ofNullable(initialStock).orElse(defaultZero);
        BigDecimal lastCost = Optional.ofNullable(initialLastCost).orElse(defaultZero);

        BigDecimal incomingValue = avgCost.equals(defaultZero) || incomingQty.equals(defaultZero)
                ? defaultZero
                : incomingQty.multiply(avgCost);

        return StockRaw.builder()
                .avgCost(avgCost)
                .incomingQty(incomingQty)
                .qtyOnHand(incomingQty)
                .lastCost(lastCost)
                .incomingValue(incomingValue)
                .inventoryValue(incomingValue)
                .build();
    }


    @Override
    public BigDecimal calculateAvgPrice(BigDecimal inventoryValue, BigDecimal oldQty, BigDecimal oldAvgCost, BigDecimal incomingQty, BigDecimal incomingPrice, Boolean isReduce) {
        return null;
    }
}
