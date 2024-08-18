package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateRawIngredientRequest;
import com.feriwinarta.library.model.CreateRawIngredientResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RawIngredientService {
    CreateRawIngredientResponse create(MultipartFile image, CreateRawIngredientRequest request);
}
