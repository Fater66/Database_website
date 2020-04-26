package com.fater.wds.exceptions;

public class PolicyOperationException extends RuntimeException{
	
	//inform administrator of type of exception
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6040180535323771378L;

	public PolicyOperationException(String msg)
	{
		super(msg);
	}
}
