package com.finance_manager.auth_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.model.UserModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "user-service", url = "http://localhost:8504/user")
public interface UserClient
{
	@PostMapping ("/saveUser")
	ResponseStructure <Void> saveUser (@RequestBody UserModel userModel);
}