package com.finance_manager.model;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class UserModel
{
	@NotBlank (message = "Email can't be blank")
	private String email;
	private String name;
}