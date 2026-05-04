package com.finance_manager.frontend_service.service;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.frontend_service.client.BudgetClient;
import com.finance_manager.model.BudgetModel;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BudgetServiceImplementation implements BudgetService
{
	private final BudgetClient budgetClient;
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
	public void deleteBudget (UUID id)
	{
		budgetClient.deleteBudget (id);
	}
}