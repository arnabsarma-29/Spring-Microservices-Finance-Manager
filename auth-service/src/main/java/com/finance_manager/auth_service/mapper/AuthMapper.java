package com.finance_manager.auth_service.mapper;
import com.finance_manager.auth_service.dto.UserDTO;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.auth_service.model.UserLoginModel;
import com.finance_manager.auth_service.model.UserModel;
public interface AuthMapper
{
	User toUser (UserModel userModel);
	User toUser (UserLoginModel userLoginModel);
	UserDTO toUserDTO (User user);
	org.springframework.security.core.userdetails.UserDetails toUserDetails (User user);
	User toUser (UserDTO userDTO);
}