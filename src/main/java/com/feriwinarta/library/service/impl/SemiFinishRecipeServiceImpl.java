package com.feriwinarta.library.service.impl;

import com.feriwinarta.library.entity.Ingredient;
import com.feriwinarta.library.entity.SemiFinishIngredient;
import com.feriwinarta.library.entity.SemiFinishRecipe;
import com.feriwinarta.library.entity.SemiFinishRecipeIngredient;
import com.feriwinarta.library.exception.ResourceNotFoundException;
import com.feriwinarta.library.model.CreateSemiFinishRecipeRequest;
import com.feriwinarta.library.model.SemiFinishRecipeResponse;
import com.feriwinarta.library.repository.SemiFinishIngredientRepository;
import com.feriwinarta.library.repository.SemiFinishRecipeIngredientRepository;
import com.feriwinarta.library.repository.SemiFinishRecipeRepository;
import com.feriwinarta.library.service.SemiFinishRecipeService;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SemiFinishRecipeServiceImpl implements SemiFinishRecipeService {
    @Setter(onMethod_ = {@Autowired})
    private SemiFinishIngredientRepository semiFinishIngredientRepository;

    @Setter(onMethod_ = {@Autowired})
    private SemiFinishRecipeRepository semiFinishRecipeRepository;

    @Setter(onMethod_ = {@Autowired})
    private SemiFinishRecipeIngredientRepository semiFinishRecipeIngredientRepository;

    @Override
    @Transactional
    public SemiFinishRecipeResponse create(CreateSemiFinishRecipeRequest request) {
        validateExistingSemiFinishIngredient(request.getSemiFinishIngredientId());

        // create recipe
        SemiFinishRecipe semiFinishRecipe = SemiFinishRecipe.builder()
                .outcome(request.getOutcome())
                .build();
        semiFinishRecipeRepository.save(semiFinishRecipe);

        List<Map<String, Long>> ingredients = request.getIngredients();

        // create recipe ingredients
        List<SemiFinishRecipeIngredient> semiFinishRecipeIngredients = new ArrayList<>();
        for (Map<String, Long> ingredientMap : ingredients) {
            for (Map.Entry<String, Long> entry : ingredientMap.entrySet()) {
                String ingredientId = entry.getKey();
                Long amount = entry.getValue();

                Ingredient ingredient1 = new Ingredient();
                ingredient1.setId(ingredientId);

                SemiFinishRecipeIngredient recipe = SemiFinishRecipeIngredient.builder()
                        .usageAmount(amount)
                        .ingredient(ingredient1)
                        .recipe(semiFinishRecipe)
                        .build();

                semiFinishRecipeIngredients.add(recipe);
            }
        }

        semiFinishRecipeIngredientRepository.saveAll(semiFinishRecipeIngredients);

        return SemiFinishRecipeResponse.builder()
                .id(semiFinishRecipe.getId())
                .build();
    }

    private SemiFinishIngredient validateExistingSemiFinishIngredient(String semiFinishIngredientId) {
        return semiFinishIngredientRepository.findById(semiFinishIngredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Item tidak ditemukan"));
    }
}
