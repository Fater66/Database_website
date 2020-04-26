package com.fater.wds.exceptions;

public class PaymentOperationException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8955102830157132999L;

	public PaymentOperationException(String msg)
	{
		super(msg);
	}
}
