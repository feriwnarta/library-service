package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateUnitRequest;
import com.feriwinarta.library.model.CreateUnitResponse;

public interface UnitService {
    CreateUnitResponse create(CreateUnitRequest request);

    CreateUnitResponse get(String id);
}
