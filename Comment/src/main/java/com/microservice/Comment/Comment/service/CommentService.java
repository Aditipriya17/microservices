package com.microservice.Comment.Comment.service;

import com.microservice.Comment.Comment.config.RestTemplateConfig;
import com.microservice.Comment.Comment.entity.Comment;
import com.microservice.Comment.Comment.payload.Post;
import com.microservice.Comment.Comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateConfig restTemplate;


    // Microservice implementation interacting with one  via url//
    public Comment saveComment(Comment comment) {
        Post post = restTemplate.getRestTemplate().getForObject("http://localhost:8083/api/posts/" + comment.getPostId(), Post.class);
        if (post != null) {
            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);
            Comment savedComment = commentRepository.save(comment);
            return savedComment;

        } else {
            return null;
        }


    }

    ;


    public List<Comment> getAllCommentsByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }
}


