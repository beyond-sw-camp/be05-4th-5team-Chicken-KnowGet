package com.knowget.knowget.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailRequestDTO {

	@Email
	@NotEmpty(message = "이메일을 입력해 주세요")
	private String email;

}
