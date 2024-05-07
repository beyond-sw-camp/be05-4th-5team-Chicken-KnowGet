package com.knowget.knowget.domain.comment.service;

import java.util.List;

import com.knowget.knowget.domain.comment.dto.CommentRequestDto;
import com.knowget.knowget.domain.comment.dto.CommentUpdateDto;
import com.knowget.knowget.global.entity.Comment;

public interface CommentService {
    //create
    public String createComment(Long postIdx, CommentRequestDto commentRequestDto);
    //read
    public List<Comment> getComments(Long postIdx);
    //update
    public String updateComment(Long commentIdx, CommentUpdateDto commentUpdateDto);
    //delete
    public String deleteComment(Long commentIdx, String userId);
}