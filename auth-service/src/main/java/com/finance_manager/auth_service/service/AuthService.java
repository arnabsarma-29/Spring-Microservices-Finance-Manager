package com.finance_manager.auth_service.service;
import com.finance_manager.auth_service.dto.AuthResponseDTO;
import com.finance_manager.auth_service.dto.UserDTO;
import com.finance_manager.auth_service.model.PasswordUpdateModel;
import com.finance_manager.auth_service.model.UserLoginModel;
import com.finance_manager.auth_service.model.UserModel;
import jakarta.validation.Valid;
public interface AuthService
{
	UserDTO register (@Valid UserModel userModel);
	AuthResponseDTO login (@Valid UserLoginModel request);
	void updatePassword (@Valid PasswordUpdateModel passwordUpdateModel);
	void deleteUser ();
}