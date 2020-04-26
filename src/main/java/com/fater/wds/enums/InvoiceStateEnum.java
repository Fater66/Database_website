package com.fater.wds.enums;

public enum InvoiceStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"INVOICE EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_INVOICEID(-1002,"INVOICE ID is NULL"),NULL_INVOICE(-1003,"EMPTY INVOICE");
	
	private int state;
	private String stateInfo;
	
	private InvoiceStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static InvoiceStateEnum stateOf(int state)
	{
		for(InvoiceStateEnum stateEnum:values())
		{
			if(stateEnum.getState() == state)
			{
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
}
