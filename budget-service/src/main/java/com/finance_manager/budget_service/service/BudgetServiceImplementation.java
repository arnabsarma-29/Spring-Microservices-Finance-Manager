package com.finance_manager.budget_service.service;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.finance_manager.budget_service.client.UserClient;
import com.finance_manager.budget_service.dao.BudgetDAO;
import com.finance_manager.budget_service.dto.BudgetDTO;
import com.finance_manager.budget_service.mapper.BudgetMapper;
import com.finance_manager.budget_service.model.BudgetModel;
import com.finance_manager.exception.CustomException;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BudgetServiceImplementation implements BudgetService
{
	private final BudgetDAO budgetDAO;
	private final BudgetMapper budgetMapper;
	private final UserClient userClient;
	@Override
	public void save (BudgetModel budgetModel)
	{
		try
		{
			budgetDAO.save (budgetMapper.toBudget (budgetModel));
		}
		catch (Exception e)
		{
			throw new CustomException ("Failed to save budget!", e);
		}
	}
	@Override
	public void delete (UUID id)
	{
		if (!budgetDAO.existsById (id))
		{
			throw new CustomException ("Budget not found with id: " + id);
		}
		budgetDAO.delete (id);
	}
	@Override
	public BudgetDTO getBudget ()
	{
		return budgetMapper.toBudgetDTO (budgetDAO.getBudget (userClient.getId ()).orElseThrow (() -> new CustomException ("Budget not found")));
	}
}