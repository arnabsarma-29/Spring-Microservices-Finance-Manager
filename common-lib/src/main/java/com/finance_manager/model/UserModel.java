package com.finance_manager.model;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel
{
	@NotNull
	private UUID id;
	@NotBlank (message = "Email can't be blank")
	private String email;
	private String name;
}