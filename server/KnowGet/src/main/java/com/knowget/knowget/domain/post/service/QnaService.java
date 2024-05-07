package com.knowget.knowget.domain.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knowget.knowget.domain.post.dto.QnaRequestDTO;
import com.knowget.knowget.domain.post.dto.QnaModifyRequestDTO;
import com.knowget.knowget.domain.post.exception.InvalidLoginException;
import com.knowget.knowget.domain.post.exception.QnaNotFoundException;
import com.knowget.knowget.domain.post.repository.PostRepository;
import com.knowget.knowget.domain.user.repository.UserRepository;
import com.knowget.knowget.global.entity.Post;
import com.knowget.knowget.global.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	/**
	 * 최신순으로 Q&A 리스트 조회
	 *
	 * @return
	 */
	public List<Post> findAll() {
		return postRepository.findAllByOrderByWrittenTimeDesc();
	}

	/**
	 * Q&A 조회
	 *
	 * @param id
	 * @return
	 */
	public Post findById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
		return post;
	}

	/**
	 * Q&A 생성
	 *
	 * @param
	 * @return
	 */
	@Transactional
	public String save(QnaRequestDTO qnaRequestDTO) {

		Optional<User> user = userRepository.findByUserId(qnaRequestDTO.getId());
		if (user.isPresent()) {
			Post qna = Post.builder()
				.title(qnaRequestDTO.getTitle())
				.content(qnaRequestDTO.getContent())
				.user(user.get())
				.type(qnaRequestDTO.getType())
				.build();
			postRepository.save(qna);
			return "게시글이 저장되었습니다.";
		} else {
			throw new InvalidLoginException("잘못된 로그인입니다.");
		}
	}

	/**
	 * Q&A 수정
	 *
	 * @param idx
	 * @param qnaModifyRequestDTO
	 */
	@Transactional
	public String update(Long idx, QnaModifyRequestDTO qnaModifyRequestDTO) {
		Optional<User> user = userRepository.findByUserId(qnaModifyRequestDTO.getId());
		Post qna = postRepository.findById(idx).orElseThrow(() -> new QnaNotFoundException("존재하지 않는 게시물입니다."));
		if (user.isPresent()) {
			if(qna.getUser().equals(user.get())) {
				qna.update(qnaModifyRequestDTO.getTitle(), qnaModifyRequestDTO.getContent());
				return "게시글이 수정되었습니다.";
			}else {
				throw new InvalidLoginException("로그인 후 이용해주세요.");
			}
		} else {
			throw new InvalidLoginException("로그인 후 이용해주세요.");
		}



	}

	/**
	 * Q&A 삭제
	 *
	 * @param idx
	 */
	@Transactional
	public String delete(Long idx, QnaModifyRequestDTO qnaModifyRequestDTO) {
		Post qna = postRepository.findById(idx).orElseThrow(() -> new QnaNotFoundException("존재하지 않는 게시글입니다."));
		Optional<User> user = userRepository.findByUserId(qnaModifyRequestDTO.getId());
		if (user.isPresent()) {
			if(qna.getUser().equals(user.get())) {
				postRepository.delete(qna);
				return "게시글이 삭제되었습니다.";
			} else {
				throw new InvalidLoginException("로그인 후 이용해주세요.");
			}
		} else {
			throw new InvalidLoginException("로그인 후 이용해주세요.");
		}


	}

}
