package com.knowget.knowget.domain.comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.knowget.knowget.domain.comment.dto.CommentRequestDto;
import com.knowget.knowget.domain.comment.dto.CommentUpdateDto;
import com.knowget.knowget.domain.comment.repository.CommentRepository;
import com.knowget.knowget.domain.post.repository.PostRepository;
import com.knowget.knowget.domain.user.repository.UserRepository;
import com.knowget.knowget.global.entity.Comment;
import com.knowget.knowget.global.entity.Post;
import com.knowget.knowget.global.entity.User;

public class CommentServiceImpl implements CommentService{
    
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //작성
    @Override
    public String createComment(Long postIdx, CommentRequestDto commentRequestDto) {
        Optional<User> user = userRepository.findByUserId(commentRequestDto.getId());
        Optional<Post> post = postRepository.findById(postIdx);
        String msg = "";

        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Comment comment = Comment.builder()
            .post(post.get())
            .user(user.get())
            .content(commentRequestDto.getContent())
            .build();
        Comment result = commentRepository.save(comment);

        if (result == null) {
            msg = "댓글이 작성되지 않았습니다.";
        }
        else {
            msg = "댓글이 작성되었습니다.";
        }

        return msg;
    }

    //모든 댓글 조회
    @Override
    public List<Comment> getComments(Long postIdx) {
        Optional<List<Comment>> comments = commentRepository.findByPostIdx(postIdx);
        if (comments.isEmpty()) {
            return null;
        }
        return comments.get();
    }

    //수정
    @Override
    public String updateComment(Long commentIdx, CommentUpdateDto commentUpdateDto) {
        try{
            commentRepository.updateComment(commentIdx, commentUpdateDto.getContent());
            return "댓글이 수정되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "댓글 수정에 실패했습니다.";
        }
    }
    
    //삭제
    @Override
    public String deleteComment(Long commentIdx) {
        try {
            commentRepository.deleteById(commentIdx);
            return "댓글이 삭제되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "댓글 삭제에 실패했습니다.";
        }
    }
}
