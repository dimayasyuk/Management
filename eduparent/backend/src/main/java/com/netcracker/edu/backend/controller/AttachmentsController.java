package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Attachments;
import com.netcracker.edu.backend.service.AccountService;
import com.netcracker.edu.backend.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentsController {

    private AttachmentService service;

    @Autowired
    public AttachmentsController(AttachmentService service) {
        this.service = service;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Iterable<Attachments> getAllAttachmentsByTask(@PathVariable(name = "id") Long taskId) {
        return service.getAllAttachments(taskId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveFile(@RequestParam("attachment") MultipartFile file, @RequestParam("id") String id) throws IOException {
        if (file != null) {
            service.saveFile(file, id);
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAttachment(@PathVariable(name = "id") Long id) {
        service.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/files/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) throws IOException {
        Attachments attachment = service.getAttachmentById(id);

        Path path = service.loadFile(attachment);

        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + attachment.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(data.length)
                .body(resource);
    }
}
