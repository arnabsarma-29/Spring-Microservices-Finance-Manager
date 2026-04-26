package com.finance_manager.user_service.model;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserDeleteModel
{
	@NotBlank (message = "Password can't be blank")
	private String password;
}