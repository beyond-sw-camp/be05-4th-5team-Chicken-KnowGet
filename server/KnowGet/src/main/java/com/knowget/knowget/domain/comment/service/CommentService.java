package com.knowget.knowget.domain.comment.service;

import java.util.List;

import com.knowget.knowget.domain.comment.dto.CommentRequestDto;
import com.knowget.knowget.domain.comment.dto.CommentUpdateDto;
import com.knowget.knowget.global.entity.Comment;

public interface CommentService {
	//create
	String createComment(CommentRequestDto commentRequestDto);

	//read
	List<Comment> getComments(Long postIdx);

	//update
	String updateComment(Long commentIdx, CommentUpdateDto commentUpdateDto);

	//delete
	String deleteComment(Long commentIdx, String userId);
}