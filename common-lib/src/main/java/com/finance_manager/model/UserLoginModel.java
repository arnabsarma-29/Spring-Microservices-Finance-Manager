package com.finance_manager.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginModel
{
	@Email (message = "Invalid email format")
	@NotBlank (message = "Email can't be blank")
	private String email;
	@NotBlank (message = "Password can't be blank")
	private String password;
}