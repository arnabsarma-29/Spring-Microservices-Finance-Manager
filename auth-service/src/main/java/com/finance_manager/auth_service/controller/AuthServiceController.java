package com.finance_manager.auth_service.controller;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.auth_service.service.AuthService;
import com.finance_manager.dto.AuthResponseDTO;
import com.finance_manager.dto.AuthUserDTO;
import com.finance_manager.mapper.RequestMapper;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.UserLoginModel;
import com.finance_manager.model.AdminUserModel;
import com.finance_manager.response.ResponseStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthServiceController
{
	private final RequestMapper requestMapper;
	private final AuthService authService;
	@PostMapping ("/signup")
	public ResponseEntity <ResponseStructure <AuthUserDTO>> signup (@Valid @RequestBody AdminUserModel userModel)
	{
		return requestMapper.buildGenerateResponse ("User registered successfully!", HttpStatus.CREATED, authService.register (userModel));
	}
	@PostMapping ("/login")
	public ResponseEntity <ResponseStructure <AuthResponseDTO>> login (@Valid @RequestBody UserLoginModel userLoginModel)
	{
		return requestMapper.buildGenerateResponse ("Login successful!", HttpStatus.OK, authService.login (userLoginModel));
	}
	@PutMapping ("/updatePassword")
	public ResponseEntity <ResponseStructure <String>> updatePassword (@Valid @RequestBody PasswordUpdateModel passwordUpdateModel)
	{
		authService.updatePassword (passwordUpdateModel);
		return requestMapper.buildGenerateResponse ("Password updated successfully!", HttpStatus.OK);
	}
	@DeleteMapping ("/delete")
	public ResponseEntity <ResponseStructure <Void>> deleteUser (String password)
	{
		authService.deleteUser (password);
		return requestMapper.buildGenerateResponse ("User deleted successfully!", HttpStatus.OK);
	}
	@GetMapping ("/getId")
	public UUID getId ()
	{
		return authService.getId ();
	}
	@GetMapping ("/getEmail")
	public String getEmail ()
	{
		return authService.getEmail ();
	}
	
}