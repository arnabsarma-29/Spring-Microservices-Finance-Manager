package com.finance_manager.frontend_service.service;
import java.util.List;
import java.util.UUID;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.frontend_service.client.AuthClient;
import com.finance_manager.frontend_service.client.BudgetClient;
import com.finance_manager.frontend_service.client.TransactionClient;
import com.finance_manager.frontend_service.client.UserClient;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.model.UserDeleteModel;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class FrontendServiceImplementation implements FrontendService
{
	private final AuthClient authClient;
	private final BudgetClient budgetClient;
	private final TransactionClient transactionClient;
	private final UserClient userClient;
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
	@Override
	public List <BudgetDTO> getAllBudgets ()
	{
		return budgetClient.getAllBudgets ().getData ();
	}
	@Override
	public void saveBudget (BudgetModel budgetModel)
	{
		budgetClient.saveBudget (budgetModel);
	}
	@Override
	public void editBudget (BudgetModel budgetModel)
	{
		budgetClient.editBudget (budgetModel);
	}
	@Override
	public List <TransactionDTO> getMonthlyTransactions ()
	{
		return transactionClient.getMonthlyTransactions ().getData ();
	}
	@Override
	public List <TransactionDTO> getAllTransactions ()
	{
		return transactionClient.getAllTransactions ().getData ();
	}
	@Override
	public void saveTransaction (TransactionModel transactionModel)
	{
		transactionClient.saveTransaction (transactionModel);
	}
	@Override
	public void deleteTransaction (UUID id)
	{
		transactionClient.deleteTransaction (id);
	}
}