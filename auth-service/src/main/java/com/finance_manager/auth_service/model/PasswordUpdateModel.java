package com.finance_manager.auth_service.model;
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
public class PasswordUpdateModel
{
	@NotBlank (message = "Current password is required")
	private String currentPassword;
	@NotBlank(message = "New password is required")
	// @Pattern (regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters, include an uppercase letter, a lowercase letter, a number, and a special character")
	@Size (min = 8, message = "Password must be at least 8 characters")
	private String newPassword;
}