package com.finance_manager.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserModel
{
	@Email (message = "Invalid email format")
	@NotBlank (message = "Email can't be blank")
	private String email;
	@Size (min = 3, message = "Password must be at least 3 characters")
	@NotBlank (message = "Password can't be blank")
	// @Pattern (regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters, include an uppercase letter, a lowercase letter, a number, and a special character")
	private String password;
}