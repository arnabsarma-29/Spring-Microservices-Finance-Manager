package com.finance_manager.frontend_service.service;
import com.finance_manager.dto.AuthResponseDTO;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.model.AuthUserModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.UserDeleteModel;
import com.finance_manager.model.UserLoginModel;
public interface UserService
{
	void register (AuthUserModel authUserModel);
	AuthResponseDTO login (UserLoginModel userLoginModel);
	UserDTO getUser ();
	void updatePassword (PasswordUpdateModel passwordUpdateModel);
	void editName (String name);
	void deleteUser (UserDeleteModel userDeleteModel);
}