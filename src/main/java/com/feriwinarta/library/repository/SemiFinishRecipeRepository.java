package com.feriwinarta.library.repository;

import com.feriwinarta.library.entity.SemiFinishRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemiFinishRecipeRepository extends JpaRepository<SemiFinishRecipe, String> {
}
