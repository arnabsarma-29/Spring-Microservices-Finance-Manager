package com.finance_manager.user_service.model;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel
{
	@NotBlank (message = "Email can't be blank")
	private String email;
	private String name;
}