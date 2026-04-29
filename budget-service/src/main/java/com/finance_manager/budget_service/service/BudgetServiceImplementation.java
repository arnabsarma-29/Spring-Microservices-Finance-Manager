package com.finance_manager.budget_service.service;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance_manager.budget_service.dao.BudgetDAO;
import com.finance_manager.budget_service.mapper.BudgetMapper;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.exception.CustomException;
import com.finance_manager.model.BudgetModel;
import com.finance_manager.security.CustomPrincipal;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BudgetServiceImplementation implements BudgetService
{
	private final BudgetDAO budgetDAO;
	private final BudgetMapper budgetMapper;
	@Override
	@Transactional
	public void save (BudgetModel budgetModel)
	{
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		budgetModel.setUserId (customPrincipal.getId ());
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
	@Transactional
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
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		return budgetMapper.toBudgetDTO (budgetDAO.getBudget (customPrincipal.getId ()).orElseThrow (() -> new CustomException ("Budget not found")));
	}
	private Optional <CustomPrincipal> getCurrentUser ()
	{
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		return (principal instanceof CustomPrincipal) ? Optional.of ((CustomPrincipal) principal) : Optional.empty ();
	}
}