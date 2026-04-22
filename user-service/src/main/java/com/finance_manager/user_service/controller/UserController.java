package com.finance_manager.user_service.controller;
import org.springframework.web.bind.annotation.RestController;
import com.finance_manager.user_service.entity.User;
import com.finance_manager.user_service.mapper.RequestMapper;
import com.finance_manager.user_service.model.UserModel;
import com.finance_manager.user_service.response.ResponseStructure;
import com.finance_manager.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@AllArgsConstructor
public class UserController
{
	private final UserService userService;
	private final RequestMapper requestMapper;
	@PostMapping ("path")
	public ResponseEntity <ResponseStructure <Object>> saveUser (@RequestBody UserModel usermModel)
	{
		userService.saveUser (usermModel);
		return requestMapper.buildGenerateResponse ("User saved successfully!");
	}
	@DeleteMapping ("path/{id}")
	public ResponseEntity <ResponseStructure <Object>> deleteUser (@PathVariable )
	{
		return ;
	}
}