package com.finance_manager.frontend_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.finance_manager.dto.AuthResponseDTO;
import com.finance_manager.dto.AuthUserDTO;
import com.finance_manager.model.AuthUserModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.UserLoginModel;
import com.finance_manager.response.ResponseStructure;
@FeignClient (name = "auth-service", url = "http://localhost:8500/auth")
public interface AuthClient
{
	@PostMapping ("/signup")
	ResponseStructure <AuthUserDTO> signup (@RequestBody AuthUserModel userModel);
	@PostMapping ("/login")
	ResponseStructure <AuthResponseDTO> login (@RequestBody UserLoginModel userLoginModel);
	@PutMapping ("/updatePassword")
	ResponseStructure <String> updatePassword (@RequestBody PasswordUpdateModel passwordUpdateModel);
}