package com.knowget.knowget.domain.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knowget.knowget.domain.comment.dto.CommentRequestDto;
import com.knowget.knowget.domain.comment.dto.CommentUpdateDto;
import com.knowget.knowget.domain.comment.service.CommentService;
import com.knowget.knowget.global.entity.Comment;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	//작성
	@PostMapping("/comment/create")
	public ResponseEntity<String> createComment(@RequestBody CommentRequestDto commentRequestDto) {
		if (commentRequestDto.getContent() == null || commentRequestDto.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		commentRequestDto.setId(userId);

		String msg = commentService.createComment(commentRequestDto);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	//특정 게시글의 모든 댓글 조회
	@PostMapping(value = "/qna/{postIdx}/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Comment>> getComments(@PathVariable("postIdx") Long postIdx) {
		List<Comment> comments = commentService.getComments(postIdx);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

	//수정
	@PutMapping("/comment/update/{commentIdx}")
	public ResponseEntity<String> updateComment(@PathVariable("commentIdx") Long commentIdx,
		@RequestBody CommentUpdateDto commentUpdateDto) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		commentUpdateDto.setId(userId);
		String msg = commentService.updateComment(commentIdx, commentUpdateDto);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	//삭제
	@DeleteMapping("/comment/delete/{commentIdx}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentIdx") Long commentIdx) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		String msg = commentService.deleteComment(commentIdx, userId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}