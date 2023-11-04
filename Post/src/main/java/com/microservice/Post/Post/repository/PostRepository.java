package com.microservice.Post.Post.repository;

import com.microservice.Post.Post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
