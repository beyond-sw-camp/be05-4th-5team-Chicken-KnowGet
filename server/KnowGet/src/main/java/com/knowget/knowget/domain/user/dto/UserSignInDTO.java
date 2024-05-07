package com.knowget.knowget.domain.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignInDTO {

	@Size(min = 3, max = 255)
	private String id;

	@Size(min = 3, max = 255)
	private String password;

}
