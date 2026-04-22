package com.finance_manager.user_service.mapper;
import com.finance_manager.user_service.dto.UserDTO;
import com.finance_manager.user_service.entity.User;
import com.finance_manager.user_service.model.UserModel;
public interface UserMapper
{
	User toUser (UserModel userModel);
	UserDTO toUserDTO (User user);
}