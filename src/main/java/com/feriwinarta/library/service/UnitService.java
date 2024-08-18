package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateUnitRequest;
import com.feriwinarta.library.model.UnitResponse;

public interface UnitService {
    UnitResponse create(CreateUnitRequest request);
    UnitResponse get(String id);
}
