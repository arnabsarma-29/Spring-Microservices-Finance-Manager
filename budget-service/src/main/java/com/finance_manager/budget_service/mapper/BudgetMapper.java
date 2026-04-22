package com.finance_manager.budget_service.mapper;
import com.finance_manager.budget_service.dto.BudgetDTO;
import com.finance_manager.budget_service.entity.Budget;
import com.finance_manager.budget_service.model.BudgetModel;
public interface BudgetMapper
{
	Budget toBudget (BudgetModel budgetModel);
	BudgetDTO toBudgetDTO (Budget budget);
}