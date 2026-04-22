package com.finance_manager.email_service.exception;
public class EmailSendingException extends RuntimeException
{
	public EmailSendingException (String message)
	{
		super (message);
	}
	public EmailSendingException (String message, Throwable cause)
	{
		super (message, cause);
	}
}