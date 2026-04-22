package com.finance_manager.email_service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.email_service.model.EmailModel;
import com.finance_manager.email_service.response.ResponseStructure;
import com.finance_manager.email_service.service.EmailService;
import com.finance_manager.email_service.util.ResponseHandler;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
@RequestMapping ("/email")
public class EmailController
{
	private final EmailService emailService;
	@PostMapping ("/register")
	public ResponseEntity <ResponseStructure <Object>> registerEmail (@RequestBody EmailModel emailModel)
	{
		emailService.registerEmail (emailModel);
		return buildGenerateResponse ();
	}
	@PostMapping ("/login")
	public ResponseEntity <ResponseStructure <Object>> loginEmail (@RequestBody EmailModel emailModel)
	{
		emailService.loginEmail (emailModel);
		return buildGenerateResponse ();
	}
	@PostMapping ("/passwordChange")
	public ResponseEntity <ResponseStructure <Object>> passwordChange (@RequestBody EmailModel emailModel)
	{
		emailService.passwordChange (emailModel);
		return buildGenerateResponse ();
	}
	// This Endpoint should receive to's mail address and body
	@PostMapping ("/sendMonthlySummaryEmail")
	public ResponseEntity <ResponseStructure <Object>> sendMonthlySummaryEmail (@RequestBody EmailModel emailModel)
	{
		emailService.sendMonthlySummaryEmail (emailModel);
		return buildGenerateResponse ();
	}
	private ResponseEntity <ResponseStructure <Object>> buildGenerateResponse ()
	{
		return ResponseHandler.generateResponse (true, "Email sent successfully!", HttpStatus.OK, null);
	}
}