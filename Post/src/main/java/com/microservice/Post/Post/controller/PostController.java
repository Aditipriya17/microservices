package com.microservice.Post.Post.controller;

import com.microservice.Post.Post.entity.Post;
import com.microservice.Post.Post.payload.PostDto;
import com.microservice.Post.Post.service.PostService;
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
    public ResponseEntity<PostDto> getPostwithComments(@PathVariable String postId) {
        PostDto postDto = postService.getPostwithComments(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }


}
