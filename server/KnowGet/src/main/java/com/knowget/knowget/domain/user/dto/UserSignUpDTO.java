package com.knowget.knowget.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignUpDTO {

	@Size(min = 3, max = 255)
	@NotEmpty(message = "사용자 아이디는 필수항목 입니다.")
	private String id;

	@Size(max = 255)
	@NotEmpty(message = "이름은 필수항목 입니다.")
	private String name;

	@Size(min = 3, max = 255)
	@NotEmpty(message = "비밀번호는 필수항목 입니다.")
	private String password;

	@Size(max = 40)
	private String phone;

	@Email
	@Size(max = 255)
	@NotEmpty(message = "이메일은 필수항목 입니다.")
	private String email;

}
