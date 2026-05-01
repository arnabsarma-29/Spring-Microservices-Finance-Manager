package com.finance_manager.user_service.service;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.exception.CustomException;
import com.finance_manager.model.UserDeleteModel;
import com.finance_manager.model.UserModel;
import com.finance_manager.security.CustomPrincipal;
import com.finance_manager.user_service.client.AuthClient;
import com.finance_manager.user_service.client.BudgetClient;
import com.finance_manager.user_service.client.TransactionClient;
import com.finance_manager.user_service.dao.UserDAO;
import com.finance_manager.user_service.mapper.UserMapper;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService
{
	private final UserDAO userDAO;
	private final UserMapper userMapper;
	private final AuthClient authClient;
	private final BudgetClient budgetClient;
	private final TransactionClient transactionClient;
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
			throw new CustomException ("Failed to save user at " + LocalDateTime.now (), e);
		}
	}
	@Transactional
	@Override
	public void deleteUser (UserDeleteModel userDeleteModel)
	{
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		try
		{
			userDAO.deleteUser (userDAO.findById (customPrincipal.getId ()).orElseThrow (() -> new CustomException ("User Not Found")));
			authClient.deleteUser (userDeleteModel.getPassword ());
			budgetClient.deleteAllBudget (customPrincipal.getId ());
			transactionClient.deleteAllTransactions (customPrincipal.getId ());
		}
		catch (Exception e)
		{
			throw new CustomException ("Failed to delete user at " + LocalDateTime.now (), e);
		}
	}
	@Override
	public UserDTO getUser ()
	{
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		return userMapper.toUserDTO (userDAO.findById (customPrincipal.getId ()).orElseThrow (() -> new CustomException ("Unable to find user at " + LocalDateTime.now ())));
	}
	private Optional <CustomPrincipal> getCurrentUser ()
	{
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		return (principal instanceof CustomPrincipal) ? Optional.of ((CustomPrincipal) principal) : Optional.empty ();
	}
}