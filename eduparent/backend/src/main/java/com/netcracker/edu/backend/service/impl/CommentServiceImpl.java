package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Comment;
import com.netcracker.edu.backend.repository.CommentRepository;
import com.netcracker.edu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository repository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Comment> getAllComments(Long taskId) {
        return repository.findCommentsByTaskId(taskId);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }
}
