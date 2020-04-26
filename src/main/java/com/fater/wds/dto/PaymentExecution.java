package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Payment;
import com.fater.wds.enums.PaymentStateEnum;

public class PaymentExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//payment数量
	private int count;
	
	//操作的payment（增删改payment的时候用到)
	private Payment payment;
	
	//payment列表（查询payment列表的时候使用）
	private List<Payment> paymentList;
	public PaymentExecution() {
		
	}
	//店铺操作失败的构造器
	public PaymentExecution(PaymentStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public PaymentExecution(PaymentStateEnum stateEnum,Payment payment)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.payment = payment;
	}
	//操作成功的构造器
	public PaymentExecution(PaymentStateEnum stateEnum,List<Payment> paymentList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.paymentList = paymentList;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public List<Payment> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}
	
	
}
