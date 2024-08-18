package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateSemiFinishRecipeRequest;
import com.feriwinarta.library.model.CreateSemiFinishRecipeResponse;

public interface SemiFinishRecipeService {
    CreateSemiFinishRecipeResponse create(CreateSemiFinishRecipeRequest request);
}
