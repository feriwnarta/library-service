package com.feriwinarta.library.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Component
@NoArgsConstructor
public class FileUploadUtil {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    /**
     * Generates a random string of the specified length.
     *
     * @param length the length of the random string
     * @return the generated random string
     */
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Saves a multipart file to the specified location.
     *
     * @param location      the directory to save the file
     * @param multipartFile the file to save
     * @return the unique file code with its extension
     * @throws IOException if an I/O error occurs
     */
    @SneakyThrows
    public static String saveFile(String location, MultipartFile multipartFile) {

        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        Path uploadPath = Paths.get(location);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        // Check if the file's name contains invalid characters
        if (originalFilename.contains("..")) {
            throw new IOException("Filename contains invalid path sequence: " + originalFilename);
        }

        String fileCode = generateRandomString(10);
        String fileExtension = getFileExtension(originalFilename);
        String storedFileName = fileCode + fileExtension;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(storedFileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File saved: {}", filePath);
        } catch (IOException ioe) {
            log.error("Could not save file: {}", originalFilename);
            log.error(ioe.getMessage(), ioe);
            throw ioe;
        }

        return storedFileName;
    }

    /**
     * Extracts the file extension from the given filename.
     *
     * @param filename the original filename
     * @return the file extension including the dot (e.g., ".txt")
     */
    private static String getFileExtension(String filename) {
        String extension = "";
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = filename.substring(dotIndex);
        }
        return extension;
    }
}
