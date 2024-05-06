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
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTime {

	@Id
	@Column(name = "post_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false, columnDefinition = "TEXT")
	private String content;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_idx", nullable = false)
	private User user;

	@Column(name = "type", nullable = false)
	private String type;

	@Builder
	public Post(String title, String content, User user, String type) {
		this.title = title;
		this.content = content;
		this.user = user;
		this.type = type;
	}

	public void update (String title, String content) {
		this.title = title;
		this.content = content;
	}

}
