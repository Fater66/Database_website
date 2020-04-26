package com.fater.wds.enums;

public enum AccountStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"POLICY EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_ACCOUNTID(-1002,"ACCOUNT ID is NULL"),NULL_ACCOUNT(-1003,"EMPTY ACCOUNT");
	
	private int state;
	private String stateInfo;
	
	private AccountStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static AccountStateEnum stateOf(int state)
	{
		for(AccountStateEnum stateEnum:values())
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

	public String getStateInfo() {
		return stateInfo;
	}
}
