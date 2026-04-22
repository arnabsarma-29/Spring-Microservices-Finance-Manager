package com.finance_manager.auth_service.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO
{
	private String accessToken;
	private String tokenType;
	private Long expiresIn;
}