package com.finance_manager.user_service.mapper;
import org.springframework.stereotype.Component;

import com.finance_manager.dto.UserDTO;
import com.finance_manager.model.UserModel;
import com.finance_manager.user_service.entity.User;
@Component
public class UserMapperImplementation implements UserMapper
{
	@Override
	public User toUser (UserModel userModel)
	{
		return User.builder ().email (userModel.getEmail ()).name (userModel.getName ()).build ();
	}
	@Override
	public UserDTO toUserDTO (User user)
	{
		return UserDTO.builder ().id (user.getId ()).email (user.getEmail ()).name (user.getName ()).build ();
	}
}