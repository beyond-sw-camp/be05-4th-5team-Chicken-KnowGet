package com.knowget.knowget.domain.user.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowget.knowget.domain.user.SignInResponse;
import com.knowget.knowget.domain.user.dto.UserSignInDTO;
import com.knowget.knowget.domain.user.dto.UserSignUpDTO;
import com.knowget.knowget.domain.user.exception.InvalidPasswordException;
import com.knowget.knowget.domain.user.exception.UserNotFoundException;
import com.knowget.knowget.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * 사용자로부터 입력받은 ID의 중복여부 확인
	 *
	 * @param request 사용자 ID
	 * @return 중복 여부 (True or False)
	 * @author Jihwan
	 */
	@PostMapping("/check-id")
	public ResponseEntity<Boolean> checkId(@RequestBody Map<String, String> request) {
		String id = request.get("id");
		boolean exists = userService.checkId(id);
		return new ResponseEntity<>(exists, HttpStatus.OK);
	}

	/**
	 * 사용자로부터 입력받은 이메일의 중복여부 확인
	 *
	 * @param request 사용자 이메일
	 * @return 중복 여부 (True or False)
	 * @author Jihwan
	 */
	@PostMapping("check-email")
	public ResponseEntity<Boolean> checkEmail(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		boolean exists = userService.checkEmail(email);
		return new ResponseEntity<>(exists, HttpStatus.OK);
	}

	/**
	 * 사용자로부터 입력받은 값들로 회원가입을 진행
	 *
	 * @param userSignupDTO 사용자가 입력한 ID, 이름, 비밀번호, 전화번호, 이메일
	 * @return 회원가입 성공여부에 따른 결과 메시지
	 * @author Jihwan
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody @Valid UserSignUpDTO userSignupDTO) {
		String msg = userService.signUp(userSignupDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	/**
	 * 사용자 로그인
	 *
	 * @param userSignInDTO 사용자가 입력한 ID, 비밀번호
	 * @return 로그인 성공여부에 따른 결과 메시지
	 * @author Jihwan
	 */
	@PostMapping("/signin")
	public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid UserSignInDTO userSignInDTO) {
		SignInResponse msg = userService.signIn(userSignInDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	/**
	 * 사용자가 존재하지 않을 때 발생하는 예외 처리
	 *
	 * @param e 예외
	 * @return 사용자가 존재하지 않을 때 발생하는 예외 메시지
	 * @author Jihwan
	 * @see UserNotFoundException
	 * @see UserService#signIn(UserSignInDTO)
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * 비밀번호가 일치하지 않을 때 발생하는 예외 처리
	 *
	 * @param e 예외
	 * @return 비밀번호가 일치하지 않을 때 발생하는 예외 메시지
	 * @author Jihwan
	 * @see InvalidPasswordException
	 * @see UserService#signIn(UserSignInDTO)
	 */
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}

}
