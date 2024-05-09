package com.knowget.knowget.global.entity;

import com.knowget.knowget.global.common.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTime {

	@Id
	@Column(name = "comment_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_idx", nullable = false)
	private Post post;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_idx", nullable = false)
	private User user;

	@Column(name = "content", nullable = false, columnDefinition = "TEXT")
	private String content;

	@Builder
	public Comment(Post post, User user, String content) {
		this.post = post;
		this.user = user;
		this.content = content;
	}
	
	public void updateContent(String content) {
		this.content = content;
	}

}
