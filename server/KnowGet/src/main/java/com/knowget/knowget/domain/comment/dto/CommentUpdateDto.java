package com.knowget.knowget.domain.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentUpdateDto {
    @NotNull
    private String id;
    @NotNull
    private String content;
}
