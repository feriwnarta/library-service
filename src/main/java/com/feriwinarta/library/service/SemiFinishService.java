package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateSemiFinishRequest;
import com.feriwinarta.library.model.CreateSemiFinishResponse;
import org.springframework.web.multipart.MultipartFile;

public interface SemiFinishService {
    CreateSemiFinishResponse create(MultipartFile image, CreateSemiFinishRequest request);
}
