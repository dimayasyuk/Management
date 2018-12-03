package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Comment;
import com.netcracker.edu.backend.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Value("${backend.server.url}")
    private  String backendUrl;

    @Override
    public List<Comment> getAllComments(Long taskId) {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] comments = restTemplate.getForObject(backendUrl + "/api/comments/" + taskId,Comment[].class);
        return Arrays.asList(comments);
    }

    @Override
    public Comment saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendUrl + "/api/comments",comment, Comment.class).getBody();
    }
}
