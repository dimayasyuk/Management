package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    private static final Path rootLocation = Paths.get("upload-dir");

    @Override
    public void store(MultipartFile file) {

    }
}
