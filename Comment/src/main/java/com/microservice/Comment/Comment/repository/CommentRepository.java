package com.microservice.Comment.Comment.repository;

import com.microservice.Comment.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByPostId(String postId);
}
