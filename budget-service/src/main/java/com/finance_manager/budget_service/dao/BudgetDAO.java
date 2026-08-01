package com.finance_manager.budget_service.dao;
import java.util.List;
import java.util.UUID;
import com.finance_manager.budget_service.entity.Budget;
public interface BudgetDAO
{
	boolean existsById (UUID id);
	void save (Budget budget);
	void delete (UUID id);
	void deleteAll (UUID userId);
	List <Budget> getAllBudgets (UUID id);
}