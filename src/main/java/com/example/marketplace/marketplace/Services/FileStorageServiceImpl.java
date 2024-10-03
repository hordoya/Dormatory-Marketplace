package com.example.marketplace.marketplace.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String uploadDir = "uploads/";

    // Correct constructor name, without 'void'
    public FileStorageServiceImpl() throws IOException {
        // Create the directory if it doesn't exist
        Path uploadPath = Paths.get(this.uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
    }

    /**
     * Saves the file to the server and returns the file path.
     *
     * @param file MultipartFile to be saved
     * @return String - the file path of the saved file
     */
    public String saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(this.uploadDir, fileName);

        try {
            // Ensure the directory exists in case it wasn't created properly
            Path uploadPath = Paths.get(this.uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Copy the file to the target location, replacing existing file with the same name
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the relative path for accessing the file (you might return a full URL in production)
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }
}

