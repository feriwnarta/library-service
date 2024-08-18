package com.feriwinarta.library.service.impl;

import com.feriwinarta.library.entity.Category;
import com.feriwinarta.library.entity.SemiFinishIngredient;
import com.feriwinarta.library.entity.Unit;
import com.feriwinarta.library.exception.ResourceNotFoundException;
import com.feriwinarta.library.model.CreateSemiFinishRequest;
import com.feriwinarta.library.model.SemiFinishResponse;
import com.feriwinarta.library.repository.CategoryRepository;
import com.feriwinarta.library.repository.SemiFinishIngredientRepository;
import com.feriwinarta.library.repository.UnitRepository;
import com.feriwinarta.library.service.SemiFinishService;
import com.feriwinarta.library.util.FileUploadUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SemiFinishServiceImpl implements SemiFinishService {
    @Value("${file.upload.item}")
    private String fileUploadLocation;

    @Setter(onMethod = @__({@Autowired}))
    private SemiFinishIngredientRepository semiFinishRepository;
    @Setter(onMethod_ = @Autowired)
    private UnitRepository unitRepository;
    @Setter(onMethod_ = @Autowired)
    private CategoryRepository categoryRepository;

    @Override
    public SemiFinishResponse create(MultipartFile image, CreateSemiFinishRequest request) {
        // handle save file
        String imageName = FileUploadUtil.saveFile(fileUploadLocation, image);

        // validasi existing unit
        Unit unit = validateExistingUnit(request.getUnitId());
        Category category = validateExistingCategory(request.getCategoryId());

        // insert data
        // create semi finish ingredient entity
        SemiFinishIngredient ingredient = SemiFinishIngredient.builder()
                .name(request.getName())
                .code(request.getCode())
                .image(imageName)
                .unit(unit)
                .category(category)
                .stockAlert(request.getStockAlert())
                .build();

        // save
        SemiFinishIngredient finishIngredient = semiFinishRepository.save(ingredient);

        return SemiFinishResponse.builder()
                .id(finishIngredient.getId())
                .image(finishIngredient.getImage())
                .name(finishIngredient.getName())
                .unit(finishIngredient.getUnit())
                .category(finishIngredient.getCategory())
                .stockAlert(finishIngredient.getStockAlert())
                .build();
    }

    private Category validateExistingCategory(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElse(null);
    }

    private Unit validateExistingUnit(String unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new ResourceNotFoundException("unit id not found, make sure unit id is exist"));

    }
}
