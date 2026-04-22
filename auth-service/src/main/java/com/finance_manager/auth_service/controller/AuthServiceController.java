package com.finance_manager.auth_service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.auth_service.dto.AuthResponseDTO;
import com.finance_manager.auth_service.dto.UserDTO;
import com.finance_manager.auth_service.model.PasswordUpdateModel;
import com.finance_manager.auth_service.model.UserLoginModel;
import com.finance_manager.auth_service.model.UserModel;
import com.finance_manager.auth_service.response.ResponseStructure;
import com.finance_manager.auth_service.service.AuthService;
import com.finance_manager.auth_service.util.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthServiceController
{
	private final AuthService authService;
	@PostMapping ("/signup")
	public ResponseEntity <ResponseStructure <UserDTO>> signup (@Valid @RequestBody UserModel userModel)
	{
		return ResponseHandler.generateResponse (true, "User registered successfully!", HttpStatus.CREATED, authService.register (userModel));
	}
	@PostMapping ("/login")
	public ResponseEntity <ResponseStructure <AuthResponseDTO>> login (@Valid @RequestBody UserLoginModel userLoginModel)
	{
		return ResponseHandler.generateResponse (true, "Login successful!", HttpStatus.OK, authService.login (userLoginModel));
	}
	@PutMapping ("/updatePassword")
	public ResponseEntity <String> updatePassword (@Valid @RequestBody PasswordUpdateModel passwordUpdateModel)
	{
		authService.updatePassword (passwordUpdateModel);
		return ResponseEntity.ok ("Password updated successfully!");
	}
	@DeleteMapping ("/delete")
	public ResponseEntity <Void> deleteUser ()
	{
		authService.deleteUser ();
		return ResponseEntity.noContent ().build ();
	}
	
}