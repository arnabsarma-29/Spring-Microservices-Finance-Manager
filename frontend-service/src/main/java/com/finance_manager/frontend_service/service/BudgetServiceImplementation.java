package com.finance_manager.frontend_service.service;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.dto.TransactionDTO;
import com.finance_manager.frontend_service.client.BudgetClient;
import com.finance_manager.frontend_service.client.TransactionClient;
import com.finance_manager.model.BudgetModel;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BudgetServiceImplementation implements BudgetService
{
	private final BudgetClient budgetClient;
	private final TransactionClient transactionClient;
	@Override
	public List <BudgetDTO> getAllBudgets ()
	{
		List <BudgetDTO> budgets = budgetClient.getAllBudgets ().getData ();
                List <TransactionDTO> transactions = transactionClient.getMonthlyTransactions ().getData ();
                budgets.forEach (budget -> 
			{
				double spent = transactions.stream ().filter (tx -> tx.getCategory ().equalsIgnoreCase (budget.getCategory ())).filter (tx -> tx.getType ().name ().equals ("EXPENSE")).mapToDouble (TransactionDTO :: getAmount).sum ();
				budget.setSpentAmount (spent);
			}
		);
                return budgets;
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
	public void deleteBudget (UUID id)
	{
		budgetClient.deleteBudget (id);
	}
}