package com.knowget.knowget.domain.user.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knowget.knowget.domain.user.SignInResponse;
import com.knowget.knowget.domain.user.dto.UserSignInDTO;
import com.knowget.knowget.domain.user.dto.UserSignUpDTO;
import com.knowget.knowget.domain.user.exception.InvalidPasswordException;
import com.knowget.knowget.domain.user.exception.UserNotFoundException;
import com.knowget.knowget.domain.user.repository.UserRepository;
import com.knowget.knowget.global.config.security.TokenProvider;
import com.knowget.knowget.global.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;

	/**
	 * 사용자로부터 입력받은 ID의 중복여부 확인
	 *
	 * @param id 사용자 ID
	 * @return 중복 여부 (True or False)
	 * @author Jihwan
	 */
	public boolean checkId(String id) {
		return userRepository.checkId(id);
	}

	/**
	 * 사용자로부터 입력받은 이메일의 중복여부 확인
	 *
	 * @param email 사용자 이메일
	 * @return 중복 여부 (True or False)
	 * @author Jihwan
	 */
	public boolean checkEmail(String email) {
		return userRepository.checkEmail(email);
	}

	/**
	 * 사용자로부터 입력받은 값들로 회원가입을 진행
	 *
	 * @param userSignupDTO 사용자가 입력한 ID, 이름, 비밀번호, 전화번호, 이메일
	 * @return 회원가입 성공여부에 따른 결과 메시지
	 * @author Jihwan
	 */
	@Transactional
	public String signUp(UserSignUpDTO userSignupDTO) {
		User user = User.builder()
			.id(userSignupDTO.getId())
			.name(userSignupDTO.getName())
			.password(passwordEncoder.encode(userSignupDTO.getPassword()))
			.phone(userSignupDTO.getPhone())
			.email(userSignupDTO.getEmail())
			.build();
		userRepository.save(user);
		return userSignupDTO.getId() + "님 가입을 환영합니다.";
	}

	/**
	 * 사용자 로그인
	 *
	 * @param userSignInDTO 사용자가 입력한 ID, 비밀번호
	 * @return 로그인 성공여부에 따른 결과 메시지
	 * @throws UserNotFoundException    사용자가 존재하지 않을 때 발생하는 예외
	 * @throws InvalidPasswordException 비밀번호가 일치하지 않을 때 발생하는 예외
	 * @author Jihwan
	 */
	@Transactional
	public SignInResponse signIn(UserSignInDTO userSignInDTO) {
		Optional<User> user = userRepository.findByUserId(userSignInDTO.getId());
		if (user.isPresent()) {
			if (passwordEncoder.matches(userSignInDTO.getPassword(), user.get().getPassword())) {
				String token = tokenProvider.createToken(String.format("%s:%s", user.get().getId(), "User"));
				return new SignInResponse(user.get().getId(), "User", token);
			} else {
				throw new InvalidPasswordException("아이디 혹은 비밀번호가 일치하지 않습니다.");
			}
		} else {
			throw new UserNotFoundException("입력하신 정보로 가입된 사용자가 없습니다.");
		}
	}

}
