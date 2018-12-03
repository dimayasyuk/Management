package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Attachments;
import com.netcracker.edu.backend.fapi.model.Task;
import com.netcracker.edu.backend.fapi.service.AttachmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Value("${backend.server.url}")
    private String backendUrl;

    @Override
    public List<Attachments> getAllAttachments(Long taskId) {
        RestTemplate restTemplate = new RestTemplate();
        Attachments[] attachments = restTemplate.getForObject(backendUrl + "/api/attachments/" + taskId, Attachments[].class);
        return Arrays.asList(attachments);
    }

    @Override
    public void saveFile(MultipartFile file, String id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, String> pdfHeaderMap = new LinkedMultiValueMap<>();
        pdfHeaderMap.add("Content-disposition", "form-data; name=attachment; filename=" + file.getOriginalFilename());
        pdfHeaderMap.add("Content-type", file.getContentType());
        HttpEntity<byte[]> doc = new HttpEntity<>(file.getBytes(), pdfHeaderMap);

        LinkedMultiValueMap<String, Object> multipartReqMap = new LinkedMultiValueMap<>();
        multipartReqMap.add("attachment", doc);
        multipartReqMap.add("id",id);

        HttpEntity<LinkedMultiValueMap<String, Object>> reqEntity = new HttpEntity<>(multipartReqMap, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resE = restTemplate.exchange(backendUrl + "/api/attachments", HttpMethod.POST, reqEntity, String.class);
    }

    @Override
    public void deleteAttachment(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendUrl + "/api/attachments/" + id);
    }

    @Override
    public ResponseEntity<byte[]> loadFile(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendUrl + "/api/attachments/files/" + id, null , byte[].class);
    }
}
