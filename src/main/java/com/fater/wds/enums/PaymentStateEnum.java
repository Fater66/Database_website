package com.fater.wds.enums;

public enum PaymentStateEnum {
	CHECK(0,"IN REVIEW"),OFFLINE(-1,"PAYMENT EXPIRED"),SUCCESS(1,"SUCCESS"),
	PASS(2,"REVIEW DONE"),INNER_ERROR(-1001,"INNER ERROR"),NULL_PAYMENTID(-1002,"PAYMENT ID is NULL"),NULL_PAYMENT(-1003,"EMPTY PAYMENT");
	
	private int state;
	private String stateInfo;
	
	private PaymentStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static PaymentStateEnum stateOf(int state)
	{
		for(PaymentStateEnum stateEnum:values())
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
