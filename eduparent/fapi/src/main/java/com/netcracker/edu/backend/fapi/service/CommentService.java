package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments(Long taskId);
    Comment saveComment(Comment comment);
}
