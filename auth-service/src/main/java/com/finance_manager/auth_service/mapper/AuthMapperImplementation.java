package com.finance_manager.auth_service.mapper;
import com.finance_manager.auth_service.dto.UserDTO;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.auth_service.model.UserLoginModel;
import com.finance_manager.auth_service.model.UserModel;
public class AuthMapperImplementation implements AuthMapper
{
	@Override
	public User toUser (UserModel userModel)
	{
		return User.builder ().email (userModel.getEmail ()).password (userModel.getPassword ()).build ();
	}
	@Override
	public User toUser (UserLoginModel userLoginModel)
	{
		return User.builder ().email (userLoginModel.getEmail ()).password (userLoginModel.getPassword ()).build ();
	}
	@Override
	public UserDTO toUserDTO (User user)
	{
		return UserDTO.builder ().id (user.getId ()).email (user.getEmail ()).build ();
	}
	@Override
	public org.springframework.security.core.userdetails.UserDetails toUserDetails (User user)
	{
		return new org.springframework.security.core.userdetails.User (user.getEmail (),user.getPassword (), java.util.Collections.emptyList ());
	}
}