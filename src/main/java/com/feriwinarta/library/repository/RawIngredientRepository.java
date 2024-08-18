package com.feriwinarta.library.repository;

import com.feriwinarta.library.entity.RawIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawIngredientRepository extends JpaRepository<RawIngredient,String> {
}
