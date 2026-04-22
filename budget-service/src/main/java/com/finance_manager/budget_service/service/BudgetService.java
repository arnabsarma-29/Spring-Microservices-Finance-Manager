package com.finance_manager.budget_service.service;
import java.util.UUID;
import com.finance_manager.budget_service.dto.BudgetDTO;
import com.finance_manager.budget_service.model.BudgetModel;
import jakarta.validation.Valid;
public interface BudgetService
{
	void save (@Valid BudgetModel budgetModel);
	void delete (UUID id);
	BudgetDTO getBudget ();
}