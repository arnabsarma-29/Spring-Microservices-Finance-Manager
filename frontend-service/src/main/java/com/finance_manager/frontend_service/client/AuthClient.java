package com.finance_manager.frontend_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "auth-service", url = "http://localhost:8500/user")
public interface AuthClient
{
	@PostMapping ("/signup")
	ResponseStructure <UserDTO> signup (@RequestBody UserModel userModel);
	@PostMapping("/login")
	ResponseStructure <AuthResponseDTO> login (@RequestBody UserLoginModel userLoginModel);
}