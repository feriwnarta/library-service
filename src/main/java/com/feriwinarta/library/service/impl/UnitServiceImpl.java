package com.feriwinarta.library.service.impl;

import com.feriwinarta.library.entity.Unit;
import com.feriwinarta.library.exception.ResourceNotFoundException;
import com.feriwinarta.library.model.CreateUnitRequest;
import com.feriwinarta.library.model.UnitResponse;
import com.feriwinarta.library.repository.UnitRepository;
import com.feriwinarta.library.service.UnitService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {
    @Setter(onMethod = @__({@Autowired}))
    private UnitRepository unitRepository;

    @Override
    public UnitResponse create(CreateUnitRequest request) {

        Unit unit = new Unit();
        unit.setName(request.getName());
        unit.setCode(request.getCode());

        Unit unitSaved = unitRepository.save(unit);

        return UnitResponse.builder()
                .name(unitSaved.getName())
                .code(unitSaved.getCode())
                .build();
    }

    @Override
    public UnitResponse get(String id) {

        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return UnitResponse
                .builder()
                .id(unit.getId())
                .code(unit.getCode())
                .name(unit.getName())
                .build();
    }
}
