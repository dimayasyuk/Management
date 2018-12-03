package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Attachments;
import com.netcracker.edu.backend.repository.AttachmentRepository;
import com.netcracker.edu.backend.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Value("${rootDirectory}")
    private  String path;

    private AttachmentRepository repository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Attachments saveAttachments(Attachments attachments) {
        return repository.save(attachments);
    }

    @Override
    public Iterable<Attachments> getAllAttachments(Long id) {
        return repository.findAttachmentsByTaskId(id);
    }

    @Override
    public Path loadFile(Attachments attachment) throws IOException {
        return Paths.get(path + attachment.getName());
    }

    @Override
    public Attachments getAttachmentById(Long id) {
        return repository.findAttachmentsById(id);
    }

    @Override
    public void saveFile(MultipartFile mFile, String id) throws IOException {
        File file = new File(path + mFile.getOriginalFilename());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bos.write(mFile.getBytes());
        bos.flush();
        bos.close();
        saveAttachments(new Attachments(file.getName(), Long.valueOf(id), file.getAbsolutePath()));
    }

    @Override
    public void deleteAttachment(Long id) {
        repository.deleteById(id);
    }
}
