package com.finance_manager.budget_service.exception;
public class BudgetSaveException extends RuntimeException
{
	public BudgetSaveException (String message)
	{
		super (message);
	}
	public BudgetSaveException (String message, Throwable cause)
	{
		super (message, cause);
	}
}