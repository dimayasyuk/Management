package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Comment;

public interface CommentService {
    Iterable<Comment> getAllComments(Long taskId);
    Comment saveComment(Comment comment);
}
