package com.finance_manager.auth_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.model.EmailModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "email-service", url = "http://localhost:8081/email")
public interface EmailClient
{
	@PostMapping ("/register")
	ResponseEntity <ResponseStructure <Void>> sendRegisterEmail (@RequestBody EmailModel emailModel);
	@PostMapping ("/login")
	ResponseEntity <ResponseStructure <Void>> sendLoginEmail (@RequestBody EmailModel emailModel);
	@PostMapping ("/passwordChange")
	ResponseEntity <ResponseStructure <Void>> sendPasswordChange (@RequestBody EmailModel emailModel);
}