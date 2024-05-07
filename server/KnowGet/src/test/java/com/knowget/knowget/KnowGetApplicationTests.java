package com.knowget.knowget;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.knowget.knowget.domain.post.repository.PostRepository;
import com.knowget.knowget.domain.user.repository.UserRepository;
import com.knowget.knowget.global.entity.Post;
import com.knowget.knowget.global.entity.User;

@SpringBootTest
class KnowGetApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Test
	void timeAutoCreationTest() throws Exception {
		User user = User.builder()
			.id("test")
			.name("test")
			.password("test")
			.phone("test")
			.email("test@test.com")
			.build();
		userRepository.save(user);

		Post post = Post.builder()
			.title("test")
			.content("test")
			.user(user)
			.type("test")
			.build();
		Post result = postRepository.save(post);

		System.out.println(
			"createTime = " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(result.getWrittenTime()));
		System.out.println(
			"modifiedTime = " + DateTimeFormatter.ofPattern("yyyy-MM-ss HH:mm:ss").format(result.getModifiedTime()));
	}
	
}
