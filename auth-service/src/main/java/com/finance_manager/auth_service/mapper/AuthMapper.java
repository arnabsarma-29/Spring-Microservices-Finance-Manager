package com.finance_manager.auth_service.mapper;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.dto.AuthUserDTO;
import com.finance_manager.model.UserLoginModel;
import com.finance_manager.model.AdminUserModel;
public interface AuthMapper
{
	User toUser (AdminUserModel userModel);
	User toUser (UserLoginModel userLoginModel);
	AuthUserDTO toUserDTO (User user);
	org.springframework.security.core.userdetails.UserDetails toUserDetails (User user);
	User toUser (AuthUserDTO userDTO);
}