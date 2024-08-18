package com.feriwinarta.library.utils;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ErrorControllerUtil {
    public boolean isDuplicateKeyException(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getRootCause();
        return rootCause != null && rootCause.getMessage() != null
                && rootCause.getMessage().contains("duplicate key value");
    }

    public String extractFieldNameFromException(DataIntegrityViolationException ex) {
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : "";
        // Example logic for extracting field name from a PostgreSQL unique constraint violation message
        Pattern pattern = Pattern.compile("Key \\((.*?)\\)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
