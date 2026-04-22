package com.finance_manager.auth_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.auth_service.model.EmailModel;
@FeignClient (name = "email-service", url = "http://localhost:8081/email")
public interface EmailClient
{
	@PostMapping ("/register")
	void sendRegisterEmail (@RequestBody EmailModel emailModel);
	@PostMapping ("/login")
	void sendLoginEmail (@RequestBody EmailModel emailModel);
	@PostMapping ("/passwordChange")
	void sendPasswordChange (@RequestBody EmailModel emailModel);
}