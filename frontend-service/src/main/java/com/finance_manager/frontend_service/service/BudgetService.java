package com.finance_manager.frontend_service.service;
import java.util.List;
import java.util.UUID;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.model.BudgetModel;
public interface BudgetService
{
	List <BudgetDTO> getAllBudgets ();
	void saveBudget (BudgetModel budgetModel);
	void editBudget (BudgetModel budgetModel);
	void deleteBudget (UUID id);
}