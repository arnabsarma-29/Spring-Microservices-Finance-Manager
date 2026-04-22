package com.finance_manager.email_service.exception;
public class EmailSaveException extends RuntimeException
{
	public EmailSaveException (String message)
	{
		super (message);
	}
	public EmailSaveException (String message, Throwable cause)
	{
		super (message, cause);
	}
}