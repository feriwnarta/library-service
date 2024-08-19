package com.feriwinarta.library.controller;

import com.feriwinarta.library.configuration.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DistributedPropertiesController {
    private final ApplicationProperties applicationProperties;

    @GetMapping("/config")
    String data() {
        return applicationProperties.getData();
    }
}