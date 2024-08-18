package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateSemiFinishRecipeRequest;
import com.feriwinarta.library.model.SemiFinishRecipeResponse;

public interface SemiFinishRecipeService {
    SemiFinishRecipeResponse create(CreateSemiFinishRecipeRequest request);
}
