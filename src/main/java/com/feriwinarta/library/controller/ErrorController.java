package com.feriwinarta.library.controller;

import com.feriwinarta.library.exception.ResourceNotFoundException;
import com.feriwinarta.library.model.WebResponse;
import com.feriwinarta.library.utils.ErrorControllerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorController {

    private final ErrorControllerUtil errorControllerUtil;

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationException(SQLIntegrityConstraintViolationException exception) {
        log.error(exception.getMessage());
        log.error(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder().error(exception.getMessage()).build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<WebResponse<String>> uniqueConstraintViolations(DataIntegrityViolationException exception) {
        log.error(exception.getMessage());
        log.error(Arrays.toString(exception.getStackTrace()));

        ResponseEntity<WebResponse<String>> isConflict = duplicateKeyFromTable(exception);
        if (isConflict != null) return isConflict;

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(WebResponse.<String>builder().error(exception.getMessage()).build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<WebResponse<String>> resourceNotFoundException(ResourceNotFoundException exception) {
        log.error("Error : {}", exception.getMessage());
        log.error(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder().error(exception.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<List<String>>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Error : {}", exception.getMessage());
        log.error(Arrays.toString(exception.getStackTrace()));

        // Extract validation errors
        List<String> errors = exception.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errorMessage = Objects.requireNonNull(errorMessage, "");
                    return "${fieldName} ${errorMessage}".replace("${fieldName}", fieldName).replace("${errorMessage}", errorMessage);
                })
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<List<String>>builder()
                        .errors(errors)
                        .build());
    }

    private ResponseEntity<WebResponse<String>> duplicateKeyFromTable(DataIntegrityViolationException exception) {
        if (errorControllerUtil.isDuplicateKeyException(exception)) {
            String fieldName = errorControllerUtil.extractFieldNameFromException(exception);
            String errorMessage = fieldName != null
                    ? "The " + fieldName + " already exists"
                    : "A unique constraint violation occurred";

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(WebResponse.<String>builder().error(errorMessage).build());
        }
        return null;
    }

}
