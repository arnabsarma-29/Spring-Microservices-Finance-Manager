package com.finance_manager.frontend_service.service;
import java.util.List;
import java.util.UUID;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.dto.UserDTO;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.model.PasswordUpdateModel;
import com.finance_manager.model.TransactionModel;
import com.finance_manager.model.UserDeleteModel;
public interface FrontendService
{
	UserDTO getUser ();
	void updatePassword (PasswordUpdateModel passwordUpdateModel);
	void editName (String name);
	void deleteUser (UserDeleteModel userDeleteModel);
	List <BudgetDTO> getAllBudgets ();
	void saveBudget (BudgetModel budgetModel);
	void editBudget (BudgetModel budgetModel);
	List <TransactionDTO> getMonthlyTransactions ();
	List <TransactionDTO> getAllTransactions ();
	void saveTransaction (TransactionModel transactionModel);
	void deleteTransaction (UUID id);
}