package com.finance_manager.email_service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.email_service.model.EmailModel;
import com.finance_manager.email_service.service.EmailService;
import com.finance_manager.mapper.RequestMapper;
import com.finance_manager.response.ResponseStructure;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
@RequestMapping ("/email")
public class EmailController
{
	private final EmailService emailService;
	private final RequestMapper requestMapper;
	@PostMapping ("/register")
	public ResponseEntity <ResponseStructure <Void>> registerEmail (@RequestBody EmailModel emailModel)
	{
		emailService.registerEmail (emailModel);
		return requestMapper.buildGenerateResponse ("Registration email sent successfully!", HttpStatus.OK);
	}
	@PostMapping ("/login")
	public ResponseEntity <ResponseStructure <Void>> loginEmail (@RequestBody EmailModel emailModel)
	{
		emailService.loginEmail (emailModel);
		return requestMapper.buildGenerateResponse ("Login alert email sent successfully!", HttpStatus.OK);
	}
	@PostMapping ("/passwordChange")
	public ResponseEntity <ResponseStructure <Void>> passwordChange (@RequestBody EmailModel emailModel)
	{
		emailService.passwordChange (emailModel);
		return requestMapper.buildGenerateResponse ("Password change confirmation email sent successfully!", HttpStatus.OK);
	}
	// This Endpoint should receive to's mail address and body
	@PostMapping ("/sendMonthlySummaryEmail")
	public ResponseEntity <ResponseStructure <Void>> sendMonthlySummaryEmail (@RequestBody EmailModel emailModel)
	{
		emailService.sendMonthlySummaryEmail (emailModel);
		return requestMapper.buildGenerateResponse ("Monthly summary email sent successfully!", HttpStatus.OK);
	}
}