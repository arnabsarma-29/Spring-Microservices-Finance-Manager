package com.finance_manager.auth_service.exception;
public class NoSessionFound extends BaseException
{
	public NoSessionFound (String message)
	{
		super (ErrorCode.NO_SESSION_FOUND, message);
	}
}