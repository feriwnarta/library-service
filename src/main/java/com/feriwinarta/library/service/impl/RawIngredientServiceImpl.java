package com.feriwinarta.library.service.impl;

import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.entity.RawIngredient;
import com.feriwinarta.library.entity.Unit;
import com.feriwinarta.library.exception.ResourceNotFoundException;
import com.feriwinarta.library.model.CreateRawIngredientRequest;
import com.feriwinarta.library.model.CreateRawIngredientResponse;
import com.feriwinarta.library.model.StockRawRequest;
import com.feriwinarta.library.repository.CategoryRepository;
import com.feriwinarta.library.repository.RawIngredientRepository;
import com.feriwinarta.library.repository.UnitRepository;
import com.feriwinarta.library.service.RawIngredientService;
import com.feriwinarta.library.util.FileUploadUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class RawIngredientServiceImpl implements RawIngredientService {
    private final UnitRepository unitRepository;
    private final CategoryRepository categoryRepository;
    private final RawIngredientRepository rawIngredientRepository;
    private final RestTemplate restTemplate;
    @Value("${file.upload.item}")
    private String fileUploadLocation;

    @Override
    @Transactional
    public CreateRawIngredientResponse create(MultipartFile image, CreateRawIngredientRequest request) {
        // handle save file
        String imageName = FileUploadUtil.saveFile(fileUploadLocation, image);

        // search unit
        Unit unit = unitRepository.findById(request.getUnitId())
                .orElseThrow(() -> new ResourceNotFoundException("unit id not found, make sure unit id is exist"));

        // search category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("category id not found, make sure category id is exist"));

        RawIngredient raw = RawIngredient.builder()
                .name(request.getName())
                .code(request.getCode())
                .stockAlert(request.getStockAlert())
                .thumbnail(imageName)
                .category(category)
                .unit(unit)
                .build();

        RawIngredient rawIngredientSaved = rawIngredientRepository.save(raw);

        StockRawRequest stockRequest = StockRawRequest.builder()
                .ingredientId(rawIngredientSaved.getId())
                .branchId(request.getBranchId())
                .incomingQty(request.getInitialStock())
                .avgCost(request.getAverageCost())
                .lastCost(BigDecimal.ZERO)
                .build();


        restTemplate.postForObject("http://inventory/inventory/stocks/raws", stockRequest, StockRawRequest.class);


        return CreateRawIngredientResponse.builder()
                .id(rawIngredientSaved.getId())
                .name(rawIngredientSaved.getName())
                .code(rawIngredientSaved.getCode())
                .initialStock(BigDecimal.ZERO)
                .averageCost(BigDecimal.ZERO)
                .category(category)
                .unit(unit)
                .stockAlert(rawIngredientSaved.getStockAlert())
                .build();
    }
}
