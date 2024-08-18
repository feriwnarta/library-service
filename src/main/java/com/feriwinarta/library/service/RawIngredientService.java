package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateRawIngredientRequest;
import com.feriwinarta.library.model.RawIngredientResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RawIngredientService {
    public RawIngredientResponse create(MultipartFile image, CreateRawIngredientRequest request);
}
