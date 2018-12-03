package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Attachments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    List<Attachments> getAllAttachments(Long projectId);
    void saveFile(MultipartFile file, String id) throws IOException;
    void deleteAttachment(Long id);
    ResponseEntity<byte[]> loadFile(Long id);
}
