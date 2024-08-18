package com.feriwinarta.library.service;

import com.feriwinarta.library.entity.StockRaw;

import java.math.BigDecimal;

public interface InventoryValuation {
    StockRaw calculateInitialAvgCost(BigDecimal initialStock, BigDecimal initialAvgCost, BigDecimal initialLastCost);
    BigDecimal calculateAvgPrice(BigDecimal inventoryValue, BigDecimal oldQty, BigDecimal oldAvgCost, BigDecimal incomingQty, BigDecimal incomingPrice, Boolean isReduce);
}
