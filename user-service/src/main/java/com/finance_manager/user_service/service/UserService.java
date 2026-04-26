package com.finance_manager.user_service.service;
import com.finance_manager.user_service.dto.UserDTO;
import com.finance_manager.user_service.model.UserDeleteModel;
import com.finance_manager.user_service.model.UserModel;
public interface UserService
{
	void saveUser (UserModel userModel);
	void deleteUser (UserDeleteModel userDeleteModel);
	UserDTO getUser ();
}