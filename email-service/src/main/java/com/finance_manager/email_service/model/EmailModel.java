package com.finance_manager.email_service.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailModel
{
	@NotBlank
	@Email
	private String receiver;
	private String subject;
	private String body;
}