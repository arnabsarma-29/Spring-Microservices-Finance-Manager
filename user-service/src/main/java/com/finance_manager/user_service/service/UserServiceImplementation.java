package com.finance_manager.user_service.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance_manager.user_service.dao.UserDAO;
import com.finance_manager.user_service.dto.UserDTO;
import com.finance_manager.user_service.mapper.UserMapper;
import com.finance_manager.user_service.model.UserModel;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService
{
	private final UserDAO userDAO;
	private final UserMapper userMapper;
	@Transactional
	@Override
	public void saveUser (UserModel userModel)
	{
		try
		{
			userDAO.saveUser (userMapper.toUser (userModel));
		}
		catch (Exception e)
		{
			throw new Exception ();
		}
	}
	@Transactional
	@Override
	public void deleteUser (UserDeleteModel userDeleteModel)
	{
		try
		{
			userDAO.deleteUser (userDeleteModel.getId ());
		}
		catch (Exception e)
		{
			throw new Exception ();
		}
	}
	@Override
	public UserDTO getUser ()
	{
		return userMapper.toUserDTO (userDAO.getUser (user));
	}
}