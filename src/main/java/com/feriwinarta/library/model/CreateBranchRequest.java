package com.feriwinarta.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
@Builder
public class CreateBranchRequest {
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 100)
    private String code;

    @NotEmpty
    @NotBlank
    private String name;
}
