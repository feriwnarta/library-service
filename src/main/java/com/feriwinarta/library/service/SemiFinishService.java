package com.feriwinarta.library.service;

import com.feriwinarta.library.model.CreateSemiFinishRequest;
import com.feriwinarta.library.model.SemiFinishResponse;
import org.springframework.web.multipart.MultipartFile;

public interface SemiFinishService {
    SemiFinishResponse create(MultipartFile image, CreateSemiFinishRequest request);
}
