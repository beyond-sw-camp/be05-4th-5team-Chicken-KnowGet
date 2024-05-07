package com.knowget.knowget.domain.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowget.knowget.global.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByOrderByWrittenTimeDesc();
}
