package com.feriwinarta.library.service.impl;

import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.entity.RawIngredient;
import com.feriwinarta.library.entity.StockRaw;
import com.feriwinarta.library.entity.Unit;
import com.feriwinarta.library.exception.ResourceNotFoundException;
import com.feriwinarta.library.model.CreateRawIngredientRequest;
import com.feriwinarta.library.model.RawIngredientResponse;
import com.feriwinarta.library.repository.CategoryRepository;
import com.feriwinarta.library.repository.RawIngredientRepository;
import com.feriwinarta.library.repository.StockRawRepository;
import com.feriwinarta.library.repository.UnitRepository;
import com.feriwinarta.library.service.InventoryValuation;
import com.feriwinarta.library.service.RawIngredientService;
import com.feriwinarta.library.util.FileUploadUtil;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Service
public class RawIngredientServiceImpl implements RawIngredientService {

    @Value("${file.upload.item}")
    private String fileUploadLocation;
    @Setter(onMethod_ = @Autowired)
    private UnitRepository unitRepository;
    @Setter(onMethod_ = @Autowired)
    private CategoryRepository categoryRepository;
    @Setter(onMethod_ = @Autowired)
    private RawIngredientRepository rawIngredientRepository;
    @Setter(onMethod_ = @Autowired)
    private StockRawRepository stockRawRepository;
    @Setter(onMethod_ = @Autowired)
    private InventoryValuation cogsValuation;

    @Override
    @Transactional
    public RawIngredientResponse create(MultipartFile image, CreateRawIngredientRequest request) {
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

        // stock
        StockRaw stock = cogsValuation.calculateInitialAvgCost(request.getInitialStock(), request.getAverageCost(), BigDecimal.ZERO);
        stock.setBranch(request.getBranchId());
        stock.setRawIngredient(raw);

        RawIngredient rawIngredientSaved = rawIngredientRepository.save(raw);
        stockRawRepository.save(stock);

        return RawIngredientResponse.builder()
                .id(rawIngredientSaved.getId())
                .name(rawIngredientSaved.getName())
                .code(rawIngredientSaved.getCode())
                .initialStock(stock.getQtyOnHand())
                .averageCost(stock.getAvgCost())
                .category(category)
                .unit(unit)
                .stockAlert(rawIngredientSaved.getStockAlert())
                .build();
    }
}
