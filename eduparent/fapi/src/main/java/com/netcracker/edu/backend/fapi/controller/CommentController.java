package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.Comment;
import com.netcracker.edu.backend.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService service;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable(name = "id") Long taskId){
        return ResponseEntity.ok(service.getAllComments(taskId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        if(comment != null) {
            return ResponseEntity.ok(service.saveComment(comment));
        }
        return null;
    }
}
