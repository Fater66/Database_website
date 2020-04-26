package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Customer;
import com.fater.wds.enums.CustomerStateEnum;


public class CustomerExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//customer数量
	private int count;
	
	//操作的customer（增删改customer的时候用到)
	private Customer customer;
	
	//customer表（查询policy列表的时候使用）
	private List<Customer> customerList;
	public CustomerExecution() {
		
	}
	//customer操作失败的构造器
	public CustomerExecution(CustomerStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public CustomerExecution(CustomerStateEnum stateEnum,Customer customer)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.customer = customer;
	}
	//操作成功的构造器
	public CustomerExecution(CustomerStateEnum stateEnum,List<Customer> customerList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.customerList = customerList;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
