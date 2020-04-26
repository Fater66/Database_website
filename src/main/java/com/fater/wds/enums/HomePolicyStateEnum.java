package com.fater.wds.enums;

public enum HomePolicyStateEnum {

	CHECK(0,"IN REVIEW"),OFFLINE(-1,"HOME POLICY EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_HOMEID(-1002,"HOME ID is NULL"),NULL_POLICYID(-1002,"POLICY ID is NULL"),
	NULL_HOMEPOLICY(-1003,"EMPTY HOME_POLICY");
	
	private int state;
	private String stateInfo;
	
	private HomePolicyStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static HomePolicyStateEnum stateOf(int state)
	{
		for(HomePolicyStateEnum stateEnum:values())
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
