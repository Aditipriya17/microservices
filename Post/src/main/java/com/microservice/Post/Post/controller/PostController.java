package com.microservice.Post.Post.controller;

import com.microservice.Post.Post.entity.Post;
import com.microservice.Post.Post.payload.PostDto;
import com.microservice.Post.Post.service.PostService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")

public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        Post newPost = postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);

    }

    // http://localhost:8083/api/posts/{postId}
    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable String postId) {
        Post post = postService.findPostById(postId);
        return post;
    }

    // http://localhost:8083/api/posts/{postId}/comments
    @GetMapping("/{postId}/comments")
    @CircuitBreaker(name = "commentBreaker", fallbackMethod = "commentFallback")
    public ResponseEntity<PostDto> getPostWithComments(@PathVariable String postId) {
        PostDto postDto = postService.getPostWithComments(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    public ResponseEntity<PostDto> commentFallback(String postId, Exception ex) {
        System.out.println("Fallback is executed because service is down:" + ex.getMessage());

        ex.printStackTrace();

        PostDto dto = new PostDto();
        dto.setPostId("1234");
        dto.setTitle("Service Down");
        dto.setContent("Service Down");
        dto.setDescription("Service Down");

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }


}
