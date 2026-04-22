package com.finance_manager.user_service.dto;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UserDTO
{
	@NotNull
	private UUID id;
	@NotBlank (message = "Email can't be blank")
	private String email;
	@NotBlank (message = "Name can't be blank")
	private String name;
}