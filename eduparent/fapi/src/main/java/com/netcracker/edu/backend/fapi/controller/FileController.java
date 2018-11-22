package com.netcracker.edu.backend.fapi.controller;


import com.netcracker.edu.backend.fapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file){
        if(file != null) {
            System.out.println(file.getOriginalFilename());
            return ResponseEntity.ok(service.store(file));
        }
        return null;
    }
}
