package com.knowget.knowget.domain.comment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowget.knowget.global.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c WHERE c.post.idx = :postIdx")
	Optional<List<Comment>> findByPostIdx(@Param("postIdx") Long postIdx);

}
