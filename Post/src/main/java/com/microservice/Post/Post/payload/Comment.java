package com.microservice.Post.Post.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comment {

    private String commentId;

    private String name;

    private String email;

    private String body;

    private String postId;
}
