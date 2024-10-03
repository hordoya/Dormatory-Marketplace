package com.example.marketplace.marketplace.Services;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String saveFile(MultipartFile file);
}
