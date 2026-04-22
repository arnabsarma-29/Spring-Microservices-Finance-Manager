package com.finance_manager.transaction_service.exception;
public class TransactionDBException extends RuntimeException
{
	public TransactionDBException (String message)
	{
		super (message);
	}
	public TransactionDBException (String message, Throwable cause)
	{
		super (message, cause);
	}
}