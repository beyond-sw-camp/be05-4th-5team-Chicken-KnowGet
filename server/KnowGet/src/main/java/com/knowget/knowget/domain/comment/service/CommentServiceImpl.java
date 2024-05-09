package com.knowget.knowget.domain.comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knowget.knowget.domain.comment.dto.CommentRequestDto;
import com.knowget.knowget.domain.comment.dto.CommentUpdateDto;
import com.knowget.knowget.domain.comment.repository.CommentRepository;
import com.knowget.knowget.domain.post.repository.PostRepository;
import com.knowget.knowget.domain.user.repository.UserRepository;
import com.knowget.knowget.global.entity.Comment;
import com.knowget.knowget.global.entity.Post;
import com.knowget.knowget.global.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final PostRepository postRepository;

	//작성
	@Override
	public String createComment(CommentRequestDto commentRequestDto) {
		Optional<User> user = userRepository.findByUserId(commentRequestDto.getId());
		Optional<Post> post = postRepository.findById(commentRequestDto.getPostIdx());
		String msg;

		if (user.isEmpty()) {
			throw new IllegalArgumentException("User not found");
		}

		if (post.isEmpty()) {
			throw new IllegalArgumentException("Post not found");
		}

		try {
			Comment comment = Comment.builder()
				.post(post.get())
				.user(user.get())
				.content(commentRequestDto.getContent())
				.build();
			commentRepository.save(comment);
		} catch (Exception e) {
			e.printStackTrace();
			return "댓글 작성에 실패했습니다.";
		}

		msg = "댓글이 작성되었습니다.";
		return msg;
	}

	//모든 댓글 조회
	@Override
	@Transactional(readOnly = true)
	public List<Comment> getComments(Long postIdx) {
		Optional<List<Comment>> comments = commentRepository.findByPostIdx(postIdx);
		return comments.orElse(null);
	}

	//수정
	@Override
	@Transactional
	public String updateComment(Long commentIdx, CommentUpdateDto commentUpdateDto) {
		Optional<Comment> comment = commentRepository.findById(commentIdx);
		Optional<User> user = userRepository.findByUserId(commentUpdateDto.getId());

		if (comment.isEmpty()) {
			return "댓글이 존재하지 않습니다.";
		} else if (user.isEmpty()) {
			return "사용자가 존재하지 않습니다.";
		}

		if (!comment.get().getUser().getId().equals(user.get().getId())) {
			return "로그인이 필요합니다.";
		} else {
			comment.get().updateContent(commentUpdateDto.getContent());
			return "댓글이 수정되었습니다.";
		}

	}

	//삭제
	@Override
	@Transactional
//	public String deleteComment(Long commentIdx, String userId) {
//		Optional<Comment> comment = commentRepository.findById(commentIdx);
//		if (comment.isEmpty()) {
//			return "댓글이 존재하지 않습니다.";
//		}
//
//		if (!comment.get().getUser().getId().equals(userId)) {
//			return "로그인이 필요합니다.";
//		} else {
//			commentRepository.deleteById(commentIdx);
//			return "댓글이 삭제되었습니다.";
//		}
//	}
	public String deleteComment(Long commentIdx, String userId) {
		if (commentRepository.existsById(commentIdx)) {
			commentRepository.deleteById(commentIdx);
			return "Comment deleted successfully";
		} else {
			return "Comment not found";
		}
	}

}

