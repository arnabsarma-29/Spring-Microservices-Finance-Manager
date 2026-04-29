package com.finance_manager.user_service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance_manager.dto.UserDTO;
import com.finance_manager.mapper.RequestMapper;
import com.finance_manager.model.UserDeleteModel;
import com.finance_manager.model.UserModel;
import com.finance_manager.response.ResponseStructure;
import com.finance_manager.user_service.service.UserService;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
@RequestMapping ("/user")
public class UserController
{
	private final UserService userService;
	private final RequestMapper requestMapper;
	@PostMapping ("/save")
	public ResponseEntity <ResponseStructure <Void>> saveUser (@RequestBody UserModel userModel)
	{
		userService.saveUser (userModel);
		return requestMapper.buildGenerateResponse ("User saved successfully!", HttpStatus.CREATED);
	}
	@DeleteMapping ("/delete")
	public ResponseEntity <ResponseStructure <Void>> deleteUser (@RequestBody UserDeleteModel userDeleteModel)
	{
		userService.deleteUser (userDeleteModel);
		return requestMapper.buildGenerateResponse ("User deleted successfully!", HttpStatus.OK);
	}
	@GetMapping ("/getUser")
	public ResponseEntity <ResponseStructure <UserDTO>> getUser ()
	{
		return requestMapper.buildGenerateResponse ("User deleted successfully!", HttpStatus.OK, userService.getUser ());
	}
}