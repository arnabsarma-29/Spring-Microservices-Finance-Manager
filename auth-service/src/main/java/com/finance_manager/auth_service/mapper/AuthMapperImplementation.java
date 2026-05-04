package com.finance_manager.auth_service.mapper;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.dto.AuthUserDTO;
import com.finance_manager.model.UserLoginModel;
import com.finance_manager.model.AuthUserModel;
public class AuthMapperImplementation implements AuthMapper
{
	@Override
	public User toUser (AuthUserModel userModel)
	{
		return User.builder ().email (userModel.getEmail ()).password (userModel.getPassword ()).build ();
	}
	@Override
	public User toUser (UserLoginModel userLoginModel)
	{
		return User.builder ().email (userLoginModel.getEmail ()).password (userLoginModel.getPassword ()).build ();
	}
	@Override
	public AuthUserDTO toUserDTO (User user)
	{
		return AuthUserDTO.builder ().id (user.getId ()).email (user.getEmail ()).build ();
	}
	@Override
	public org.springframework.security.core.userdetails.UserDetails toUserDetails (User user)
	{
		return new org.springframework.security.core.userdetails.User (user.getEmail (),user.getPassword (), java.util.Collections.emptyList ());
	}
	@Override
	public User toUser (AuthUserDTO userDTO)
	{
		return User.builder ().email (userDTO.getEmail ()).password (null).build ();
	}
}