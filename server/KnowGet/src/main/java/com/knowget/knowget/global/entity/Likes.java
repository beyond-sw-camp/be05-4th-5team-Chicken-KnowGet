// package com.knowget.knowget.global.entity;
//
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.AccessLevel;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Entity
// @Table(name = "likes")
// @Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// public class Likes {
//
// 	@Id
// 	@Column(name = "like_idx")
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long idx;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "user_id", nullable = false)
// 	private User user;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "post_id", nullable = false)
// 	private Post post;
//
// 	@Builder
// 	public Likes(User user, Post post) {
// 		this.user = user;
// 		this.post = post;
// 	}
//
// }
