package com.finance_manager.budget_service.dao;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.finance_manager.budget_service.entity.Budget;
import com.finance_manager.budget_service.repository.BudgetRepository;
import lombok.AllArgsConstructor;
@Repository
@AllArgsConstructor
public class BudgetDAOImplementation implements BudgetDAO
{
	private final BudgetRepository budgetRepository;
	@Override
	public boolean existsById (UUID id)
	{
		return budgetRepository.existsById (id);
	}
	@Override
	public void save (Budget budget)
	{
		budgetRepository.save (budget);
	}
	@Override
	public void delete (UUID id)
	{
		budgetRepository.deleteById (id);
	}
	@Override
	public void deleteAll (UUID userId)
	{
		budgetRepository.deleteByUserId (userId);
	}
	@Override
	public List <Budget> getAllBudgets (UUID id)
	{
		return budgetRepository.findAllByUserId (id);
	}
}