package com.fater.wds.entity;

import java.sql.Date;

public class Policy {
	private Long policyId;
	private String type; //?是否还需要
	private Date startDate;
	private Date endDate;
	private Float premiumAmount;
	private String status;
	
	private Customer customer;
	
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Float getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(Float premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
