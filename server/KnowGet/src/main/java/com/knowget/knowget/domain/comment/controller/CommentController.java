package com.knowget.knowget.domain.comment.controller;

import org.springframework.web.bind.annotation.RestController;

import com.knowget.knowget.domain.comment.dto.CommentRequestDto;
import com.knowget.knowget.domain.comment.dto.CommentUpdateDto;
import com.knowget.knowget.domain.comment.service.CommentService;
import com.knowget.knowget.global.entity.Comment;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //작성
    @PostMapping("/qna/{id}/create")
    public ResponseEntity<String> createComment(@PathVariable Long postIdx, @RequestBody CommentRequestDto commentRequestDto) {
        if(commentRequestDto.getContent() == null || commentRequestDto.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String msg = commentService.createComment(postIdx, commentRequestDto);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    //특정 게시글의 모든 댓글 조회
    @PostMapping("/qna/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postIdx) {
        List<Comment> comments  = commentService.getComments(postIdx);
            return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    //수정
    @PutMapping("/qna/{id}/update/{commentIdx}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentIdx, @RequestBody CommentUpdateDto commentUpdateDto) {
        String msg = commentService.updateComment(commentIdx, commentUpdateDto);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/qna/{id}/delete/{commentIdx}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentIdx) {
        String msg = commentService.deleteComment(commentIdx);
        return new ResponseEntity<>(msg, HttpStatus.OK);        
    }
}