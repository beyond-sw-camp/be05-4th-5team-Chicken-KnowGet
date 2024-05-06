package com.knowget.knowget.domain.post.dto;

import lombok.Data;

@Data
public class QnaRequestDTO {
    private String title;
    private String content;
    private String id;
    private String type;
}
