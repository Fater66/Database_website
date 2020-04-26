package com.fater.wds.enums;

public enum AutoInsuranceStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"AutoInsurance EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_AutoInsuranceID(-1002,"AUTOINSURANCE ID is NULL"),NULL_AUTOINSURANCE(-1003,"EMPTY AUTOINSURANCE");
	
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
	
	private AutoInsuranceStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static AutoInsuranceStateEnum stateOf(int state)
	{
		for(AutoInsuranceStateEnum stateEnum:values())
		{
			if(stateEnum.getState() == state)
			{
				return stateEnum;
			}
		}
		return null;
	}
}
