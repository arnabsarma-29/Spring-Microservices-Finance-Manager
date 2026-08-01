package com.finance_manager.frontend_service.service;
import org.springframework.stereotype.Service;
import com.finance_manager.dto.AuthResponseDTO;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.frontend_service.client.AuthClient;
import com.finance_manager.frontend_service.client.UserClient;
import com.finance_manager.model.AuthUserModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.UserDeleteModel;
import com.finance_manager.model.UserLoginModel;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService
{
	private final AuthClient authClient;
	private final UserClient userClient;
	@Override
	public void register (AuthUserModel authUserModel)
	{
		authClient.signup (authUserModel);
	}
	@Override
	public AuthResponseDTO login (UserLoginModel userLoginModel)
	{
		return authClient.login (userLoginModel).getData ();
	}
	@Override
	public UserDTO getUser ()
	{
		return userClient.getUser ().getData ();
	}
	@Override
	public void updatePassword (PasswordUpdateModel passwordUpdateModel)
	{
		authClient.updatePassword (passwordUpdateModel);
	}
	@Override
	public void editName (String name)
	{
		userClient.editName (name);
	}
	@Override
	public void deleteUser (UserDeleteModel userDeleteModel)
	{
		userClient.deleteUser (userDeleteModel);
	}
}