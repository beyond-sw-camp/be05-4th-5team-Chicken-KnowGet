package com.knowget.knowget.domain.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.knowget.knowget.domain.post.dto.QnaModifyRequestDTO;
import com.knowget.knowget.domain.post.dto.QnaRequestDTO;
import com.knowget.knowget.domain.post.exception.InvalidLoginException;
import com.knowget.knowget.domain.post.exception.QnaNotFoundException;
import com.knowget.knowget.domain.post.service.QnaService;
import com.knowget.knowget.global.entity.Post;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {

	private final QnaService qnaService;

	/**
	 * 최신순으로 Q&A 리스트 조회
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Post>> findAll() {
		List<Post> postList = qnaService.findAll();
		return new ResponseEntity<>(postList, HttpStatus.OK);
	}

	/**
	 * Q&A 조회
	 */
	@PostMapping("/{postIdx}")
	public ResponseEntity<Post> findById(@PathVariable("postIdx") Long postIdx) {
		Post post = qnaService.findById(postIdx);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	/**
	 * Q&A 생성
	 */
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody QnaRequestDTO qnaRequestDTO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		qnaRequestDTO.setId(id);
		qnaRequestDTO.setType("qna");

		String msg = qnaService.save(qnaRequestDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	/**
	 * Q&A 수정
	 */
	@PatchMapping("/{postIdx}/update")
	public ResponseEntity<String> update(@PathVariable("postIdx") Long postIdx,
		@RequestBody QnaModifyRequestDTO qnaModifyRequestDTO) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		qnaModifyRequestDTO.setId(userId);
		String msg = qnaService.update(postIdx, qnaModifyRequestDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	/**
	 * Q&A 삭제
	 */
	@DeleteMapping("/{postIdx}/delete")
	public ResponseEntity<String> delete(@PathVariable("postIdx") Long postIdx) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		String msg = qnaService.delete(postIdx, userId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<String> handleInvalidLoginException(InvalidLoginException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(QnaNotFoundException.class)
	public ResponseEntity<String> handleQnaNotFoundException(QnaNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}

}
