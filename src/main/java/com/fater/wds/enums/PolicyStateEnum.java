package com.fater.wds.enums;

public enum PolicyStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"POLICY EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_POLICYID(-1002,"POLICY ID is NULL"),NULL_POLICY(-1003,"EMPTY POLICY");
	
	private int state;
	private String stateInfo;
	
	private PolicyStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static PolicyStateEnum stateOf(int state)
	{
		for(PolicyStateEnum stateEnum:values())
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
