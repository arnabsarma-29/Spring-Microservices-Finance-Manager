package com.finance_manager.auth_service.exception;
public class UserAlreadyExists extends BaseException
{
	public UserAlreadyExists (String message)
	{
		super (ErrorCode.USER_ALREADY_EXISTS, message);
	}
}