package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Attachments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface AttachmentService {
    Attachments saveAttachments(Attachments attachments);

    Iterable<Attachments> getAllAttachments(Long id);

    void saveFile(MultipartFile file, String id) throws IOException;

    void deleteAttachment(Long id);

    Path loadFile(Attachments attachment) throws IOException;

    Attachments getAttachmentById(Long id);

}
