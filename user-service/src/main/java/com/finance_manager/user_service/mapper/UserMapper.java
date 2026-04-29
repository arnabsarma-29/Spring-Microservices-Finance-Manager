package com.finance_manager.user_service.mapper;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.model.UserModel;
import com.finance_manager.user_service.entity.User;
public interface UserMapper
{
	User toUser (UserModel userModel);
	UserDTO toUserDTO (User user);
}