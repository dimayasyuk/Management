package com.netcracker.edu.backend.fapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String store(MultipartFile file);
}
