package com.finance_manager.budget_service.dao;
import java.util.Optional;
import java.util.UUID;
import com.finance_manager.budget_service.entity.Budget;
public interface BudgetDAO
{
	boolean existsById (UUID id);
	void save (Budget budget);
	void delete (UUID id);
	Optional <Budget> getBudget (UUID id);
}