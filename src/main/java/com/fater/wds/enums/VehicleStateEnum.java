package com.fater.wds.enums;

public enum VehicleStateEnum {
	CHECK(0, "IN REVIEW"), OFFLINE(-1, "HOME EXPIRED"), SUCCESS(1, "SUCCESS"), PASS(2, "REVIEW DONE"),
	INNER_ERROR(-1001, "INNER ERROR"), NULL_VEHICLEID(-1002, "VEHICLE ID is NULL"),
	NULL_VEHICLE(-1003, "EMPTY VEHICLE");
	private int state;
	private String stateInfo;

	private VehicleStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static VehicleStateEnum stateOf(int state) {
		for (VehicleStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
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
