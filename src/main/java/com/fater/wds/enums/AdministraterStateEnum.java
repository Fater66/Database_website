package com.fater.wds.enums;

public enum AdministraterStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"POLICY EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_ADMINISTRATERID(-1002,"ADMINISTRATER ID is NULL"),NULL_ADMINISTRATER(-1003,"EMPTY ADMINISTRATER");
	
	private int state;
	private String stateInfo;
	
	private AdministraterStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static AdministraterStateEnum stateOf(int state)
	{
		for(AdministraterStateEnum stateEnum:values())
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
