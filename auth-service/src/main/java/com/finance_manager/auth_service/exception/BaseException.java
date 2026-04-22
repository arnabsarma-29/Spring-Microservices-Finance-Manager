package com.finance_manager.auth_service.exception;
public class BaseException extends RuntimeException
{
	private final ErrorCode errorCode;
	public BaseException (ErrorCode errorCode, String message)
	{
		super (message);
		this.errorCode = errorCode;
	}
	public ErrorCode getErrorCode ()
	{
		return errorCode;
	}
}