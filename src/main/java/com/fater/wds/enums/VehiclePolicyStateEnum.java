package com.fater.wds.enums;

public enum VehiclePolicyStateEnum {

	CHECK(0,"IN REVIEW"),OFFLINE(-1,"VEHICLE POLICY EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_VEHICLEID(-1002,"VEHICLE ID is NULL"),NULL_POLICYID(-1002,"POLICY ID is NULL"),
	NULL_VEHICLEPOLICY(-1003,"EMPTY VEHICLE_POLICY");
	
	private int state;
	private String stateInfo;
	
	private VehiclePolicyStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static VehiclePolicyStateEnum stateOf(int state)
	{
		for(VehiclePolicyStateEnum stateEnum:values())
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
