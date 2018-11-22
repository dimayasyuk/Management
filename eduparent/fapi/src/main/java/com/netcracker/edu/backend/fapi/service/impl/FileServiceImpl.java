package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Value("${backend.server.url}")
    private  String backendUrl;

    @Override
    public String store(MultipartFile file) {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendUrl + "/api/files",file, String.class).getBody();
    }
}
