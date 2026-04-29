package com.finance_manager.auth_service.service;
import java.util.UUID;

import com.finance_manager.dto.AuthResponseDTO;
import com.finance_manager.dto.AuthUserDTO;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.UserLoginModel;
import com.finance_manager.model.AdminUserModel;

import jakarta.validation.Valid;
public interface AuthService
{
	AuthUserDTO register (@Valid AdminUserModel userModel);
	AuthResponseDTO login (@Valid UserLoginModel request);
	void updatePassword (@Valid PasswordUpdateModel passwordUpdateModel);
	void deleteUser (String password);
	UUID getId ();
	String getEmail ();
}