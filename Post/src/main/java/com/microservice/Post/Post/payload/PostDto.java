package com.microservice.Post.Post.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.events.Comment;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDto {

    private String postId;

    private String title;

    private String description;

    private String content;

    List<Comment> comments;
}
