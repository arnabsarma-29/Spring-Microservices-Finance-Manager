package com.finance_manager.security;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CustomPrincipal
{
	private UUID id;
	private String email;
}