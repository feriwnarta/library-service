package com.feriwinarta.library.repository;

import com.feriwinarta.library.entity.StockRaw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRawRepository extends JpaRepository<StockRaw, String> {
}
