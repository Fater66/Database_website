package com.fater.wds.enums;

public enum HomeStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"HOME EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_HOMEID(-1002,"HOME ID is NULL"),NULL_HOME(-1003,"EMPTY HOME");
	
	private int state;
	private String stateInfo;
	
	private HomeStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static HomeStateEnum stateOf(int state)
	{
		for(HomeStateEnum stateEnum:values())
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
