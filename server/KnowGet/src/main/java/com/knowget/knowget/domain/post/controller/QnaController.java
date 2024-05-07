package com.knowget.knowget.domain.post.controller;


import com.knowget.knowget.domain.post.dto.QnaRequestDTO;
import com.knowget.knowget.domain.post.dto.QnaModifyRequestDTO;
import com.knowget.knowget.domain.post.exception.InvalidLoginException;
import com.knowget.knowget.domain.post.exception.QnaNotFoundException;
import com.knowget.knowget.domain.post.service.QnaService;
import com.knowget.knowget.global.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;



    /**
     * 최신순으로 Q&A 리스트 조회
     * @return
     */
    @GetMapping("/findall")
    public ResponseEntity<List<Post>> findAll() {
        List<Post> postList = qnaService.findAll();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    /**
     * Q&A 조회
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post post = qnaService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * Q&A 생성
     * @param
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
     * @param id
     * @param qnaModifyRequestDTO
     */
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody QnaModifyRequestDTO qnaModifyRequestDTO) {
        String msg = qnaService.update(id, qnaModifyRequestDTO);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * Q&A 삭제
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody QnaModifyRequestDTO qnaModifyRequestDTO) {
        String msg = qnaService.delete(id, qnaModifyRequestDTO);
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
