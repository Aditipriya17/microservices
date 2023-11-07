package com.microservice.Post.Post.service;

import com.microservice.Post.Post.config.RestTemplateConfig;
import com.microservice.Post.Post.entity.Post;
import com.microservice.Post.Post.payload.PostDto;
import com.microservice.Post.Post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplateConfig restTemplate;

    // UUID is generated using cryptographically strong pseudo random number generator //
    public Post savePost(Post post) {
        String postId = UUID.randomUUID().toString();
        post.setPostid(postId);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    public Post findPostById(String postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    // if we are returing as a get for response then we have to apply response entity but here is object then it is arraylist of the object//


    public PostDto getPostWithComments(String postId) {
        Post post = postRepository.findById(postId).get();
        ArrayList comments = restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/comments/" + postId, ArrayList.class);
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostid());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(postDto.getDescription());
        postDto.setContent(postDto.getContent());
        postDto.setComments(comments);
        return postDto;
    }

}


