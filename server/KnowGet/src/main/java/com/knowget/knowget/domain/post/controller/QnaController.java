package com.knowget.knowget.domain.post.controller;


import com.knowget.knowget.domain.post.dto.QnaRequestDTO;
import com.knowget.knowget.domain.post.dto.QnaUpdateRequestDTO;
import com.knowget.knowget.domain.post.exception.InvalidLoginException;
import com.knowget.knowget.domain.post.service.QnaService;
import com.knowget.knowget.global.entity.Post;
import lombok.RequiredArgsConstructor;
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
    public List<Post> findAll() {
        List<Post> postList = qnaService.findAll();
        return postList;
    }

    /**
     * Q&A 조회
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public Post findById(@PathVariable Long id) {
        return qnaService.findById(id);
    }

    /**
     * Q&A 생성
     * @param
     */
    @ExceptionHandler(InvalidLoginException.class)
    @PostMapping("/save")
    public void save(@RequestBody QnaRequestDTO qnaRequestDTO) {
        qnaService.save(qnaRequestDTO);
    }

    /**
     * Q&A 수정
     * @param id
     * @param qnaUpdateRequestDTO
     */
    @PatchMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody QnaUpdateRequestDTO qnaUpdateRequestDTO ) {
        qnaService.update(id, qnaUpdateRequestDTO);
    }


}
