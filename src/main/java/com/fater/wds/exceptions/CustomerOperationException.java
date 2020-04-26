package com.fater.wds.exceptions;

public class CustomerOperationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5891079276154394171L;
	
	public CustomerOperationException(String msg)
	{
		super(msg);
	}
}
