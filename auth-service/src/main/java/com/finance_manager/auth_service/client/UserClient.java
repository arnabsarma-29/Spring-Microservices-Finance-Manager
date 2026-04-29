package com.finance_manager.auth_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.finance_manager.model.AdminUserModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "email-service", url = "http://localhost:8504/user")
public interface UserClient
{
	@PostMapping ("/save")
	ResponseEntity <ResponseStructure <Void>> saveUser (@RequestBody AdminUserModel userModel);
}