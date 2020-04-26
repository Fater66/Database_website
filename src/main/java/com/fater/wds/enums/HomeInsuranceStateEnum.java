package com.fater.wds.enums;

public enum HomeInsuranceStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"HomeInsurance EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_HomeInsuranceID(-1002,"HOMEINSURANCE ID is NULL"),NULL_HOMEINSURANCE(-1003,"EMPTY HOMEINSURANCE");
	
	private int state;
	private String stateInfo;
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
	
	private HomeInsuranceStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static HomeInsuranceStateEnum stateOf(int state)
	{
		for(HomeInsuranceStateEnum stateEnum:values())
		{
			if(stateEnum.getState() == state)
			{
				return stateEnum;
			}
		}
		return null;
	}
}
