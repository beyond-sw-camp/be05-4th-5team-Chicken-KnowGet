package com.knowget.knowget.domain.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDeleteDto {
    @NotNull
    private Long comment_idx;
    @NotNull
    private Long post_idx;
    @NotNull
    private Long user_idx;
    @NotNull
    private String content;

    public CommentDeleteDto(Long comment_idx, Long post_idx, Long user_idx, String content) {
        this.comment_idx = comment_idx;
        this.post_idx = post_idx;
        this.user_idx = user_idx;
        this.content = content;
    }
} 
