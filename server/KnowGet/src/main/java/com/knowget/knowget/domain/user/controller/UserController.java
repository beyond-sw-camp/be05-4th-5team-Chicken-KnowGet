package com.knowget.knowget.domain.user.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowget.knowget.domain.user.dto.UserSignUpDTO;
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

}
