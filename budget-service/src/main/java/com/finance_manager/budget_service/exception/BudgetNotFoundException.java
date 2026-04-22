package com.finance_manager.budget_service.exception;
public class BudgetNotFoundException extends RuntimeException
{
	public BudgetNotFoundException (String message)
	{
		super (message);
	}
	public BudgetNotFoundException (String message, Throwable cause)
	{
		super (message, cause);
	}
}