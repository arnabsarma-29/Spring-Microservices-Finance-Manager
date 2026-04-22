package com.finance_manager.auth_service.exception;
public enum ErrorCode
{
	USER_ALREADY_EXISTS ("AUTH_001"), INVALID_CREDENTIALS ("AUTH_002"), USER_NOT_FOUND ("AUTH_003"), NO_SESSION_FOUND ("AUTH_004"), INTERNAL_SERVER_ERROR ("AUTH_500");
	private final String code;
	ErrorCode (String code)
	{
		this.code = code;
	}
	public String getCode ()
	{
		return code;
	}
}