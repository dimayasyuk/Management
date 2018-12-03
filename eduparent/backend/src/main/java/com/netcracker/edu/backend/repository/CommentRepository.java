package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    Iterable<Comment> findCommentsByTaskId(Long taskId);
}
