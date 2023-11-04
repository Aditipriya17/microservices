package com.microservice.Comment.Comment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String postId;

    private String title;

    private String description;

    private String content;
}
