package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Comment;
import com.netcracker.edu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService service;

    @Autowired
    public CommentController(CommentService service){
        this.service = service;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Iterable<Comment> getAllComments(@PathVariable(name = "id") Long taskId) {
        return service.getAllComments(taskId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Comment saveComment(@RequestBody Comment comment){
        if(comment != null){
            service.saveComment(comment);
        }
        return null;
    }
}
