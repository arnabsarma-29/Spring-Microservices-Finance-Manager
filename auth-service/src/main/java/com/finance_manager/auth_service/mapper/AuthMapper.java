package com.finance_manager.auth_service.mapper;
import java.util.Collections;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import com.finance_manager.auth_service.entity.User;
import com.finance_manager.dto.AuthUserDTO;
import com.finance_manager.model.UserLoginModel;
import com.finance_manager.model.AuthUserModel;
@Mapper (componentModel = "spring")
public interface AuthMapper
{
	User toUser (AuthUserModel userModel);
	User toUser (UserLoginModel userLoginModel);
	AuthUserDTO toUserDTO (User user);
	default UserDetails toUserDetails (com.finance_manager.auth_service.entity.User userEntity)
	{
		if (userEntity == null)
		{
			return null;
		}
		return org.springframework.security.core.userdetails.User.builder ().username (userEntity.getEmail ()).password (userEntity.getPassword ()).authorities (Collections.emptyList ()).build ();
	}
	User toUser (AuthUserDTO userDTO);
}