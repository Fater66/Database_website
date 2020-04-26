package com.fater.wds.exceptions;

public class AccountOperationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2545742466114690669L;
	
	public AccountOperationException(String msg)
	{
		super(msg);
	}
}
