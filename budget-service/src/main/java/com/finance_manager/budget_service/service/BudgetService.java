package com.finance_manager.budget_service.service;
import java.util.List;
import java.util.UUID;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.model.BudgetModel;
import jakarta.validation.Valid;
public interface BudgetService
{
	void save (@Valid BudgetModel budgetModel);
	void edit (@Valid BudgetModel budgetModel);
	void delete (UUID id);
	void deleteAll (UUID userId);
	List <BudgetDTO> getAllBudgets ();
}