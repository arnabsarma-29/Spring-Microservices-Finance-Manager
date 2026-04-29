package com.finance_manager.budget_service.mapper;
import org.springframework.stereotype.Component;

import com.finance_manager.budget_service.entity.Budget;
import com.finance_manager.dto.BudgetDTO;
import com.finance_manager.model.BudgetModel;
@Component
public class BudgetMapperImplementation implements BudgetMapper
{
	@Override
	public Budget toBudget (BudgetModel budgetModel)
	{
		return Budget.builder ().userId (budgetModel.getUserId ()).category (budgetModel.getCategory ()).limitAmount (budgetModel.getLimitAmount ()).month (budgetModel.getMonth ()).build ();
	}
	@Override
	public BudgetDTO toBudgetDTO (Budget budget)
	{
		return BudgetDTO.builder ().id (budget.getId ()).userId (budget.getUserId ()).category (budget.getCategory ()).limitAmount (budget.getLimitAmount ()).month (budget.getMonth ()).build ();
	}
}