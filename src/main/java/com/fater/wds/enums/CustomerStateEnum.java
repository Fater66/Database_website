package com.fater.wds.enums;

public enum CustomerStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"CUstomer EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_CUSTOMERID(-1002,"CUSTOMER ID is NULL"),NULL_CUSTOMER(-1003,"EMPTY CUSTOMER");
	
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

	private int state;
	private String stateInfo;
	
	private CustomerStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static CustomerStateEnum stateOf(int state)
	{
		for(CustomerStateEnum stateEnum:values())
		{
			if(stateEnum.getState() == state)
			{
				return stateEnum;
			}
		}
		return null;
	}
}
