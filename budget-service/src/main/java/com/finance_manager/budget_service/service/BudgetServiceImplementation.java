package com.finance_manager.budget_service.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance_manager.budget_service.dao.BudgetDAO;
import com.finance_manager.budget_service.entity.Budget;
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
	@Modifying
	@Transactional
	public void edit (BudgetModel budgetModel)
	{
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		if (customPrincipal.getId ().equals (budgetModel.getUserId ()))
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
		else
		{
			throw new CustomException ("Trying To Insert Budget In Wrong User!");
		}
	}
	@Override
	@Modifying
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
	@Modifying
	@Transactional
	public void deleteAll (UUID userId)
	{
		budgetDAO.deleteAll (userId);
	}
	@Override
	public List <BudgetDTO> getAllBudgets ()
	{
		CustomPrincipal customPrincipal = getCurrentUser ().orElseThrow (() -> new CustomException ("Current User Not Found!"));
		List <Budget> budgets = budgetDAO.getAllBudgets (customPrincipal.getId ());
		return budgets.stream ().map (budgetMapper :: toBudgetDTO).collect (Collectors.toList ());
	}
	private Optional <CustomPrincipal> getCurrentUser ()
	{
		Object principal = SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
		return (principal instanceof CustomPrincipal) ? Optional.of ((CustomPrincipal) principal) : Optional.empty ();
	}
}