package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.Attachments;
import com.netcracker.edu.backend.fapi.service.AttachmentService;
import com.netcracker.edu.backend.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {
    @Autowired
    private AttachmentService service;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Attachments>> getAllAttachments(@PathVariable(name = "id") Long taskId){
        return ResponseEntity.ok(service.getAllAttachments(taskId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws IOException {
        if(file != null) {
            service.saveFile(file,id);
        }
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteAttachment(@PathVariable String id) {
        service.deleteAttachment(Long.valueOf(id));
    }

    @PostMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        return service.loadFile(id);
    }
}
