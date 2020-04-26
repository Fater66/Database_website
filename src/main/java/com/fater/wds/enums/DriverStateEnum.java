package com.fater.wds.enums;

public enum DriverStateEnum {
	CHECK(0, "IN REVIEW"), OFFLINE(-1, "HOME EXPIRED"), SUCCESS(1, "SUCCESS"), PASS(2, "REVIEW DONE"),
	INNER_ERROR(-1001, "INNER ERROR"), NULL_DRIVERID(-1002, "DRIVER ID is NULL"), NULL_DRIVER(-1003, "EMPTY DRIVER");
	
	private int state;
	private String stateInfo;

	private DriverStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static DriverStateEnum stateOf(int state) {
		for (DriverStateEnum stateEnum : values()) {
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
