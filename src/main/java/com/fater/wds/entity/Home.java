package com.fater.wds.entity;

import java.sql.Date;

public class Home {
	private Long homeId;
	private Date homePurchaseDate;
	private Float homePurchaseValue;
	private Float homeArea;
	private String homeType;
	private Integer autoFireNotification;
	private Integer homeSecuritySystem;
	private String swimmingPool;
	private Integer basement;
	private Long customerId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getBasement() {
		return basement;
	}
	public void setBasement(Integer basement) {
		this.basement = basement;
	}
	public Long getHomeId() {
		return homeId;
	}
	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}
	public Date getHomePurchaseDate() {
		return homePurchaseDate;
	}
	public void setHomePurchaseDate(Date homePurchaseDate) {
		this.homePurchaseDate = homePurchaseDate;
	}
	public Float getHomePurchaseValue() {
		return homePurchaseValue;
	}
	public void setHomePurchaseValue(Float homePurchaseValue) {
		this.homePurchaseValue = homePurchaseValue;
	}
	public Float getHomeArea() {
		return homeArea;
	}
	public void setHomeArea(Float homeArea) {
		this.homeArea = homeArea;
	}
	public String getHomeType() {
		return homeType;
	}
	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}
	
	public Integer getAutoFireNotification() {
		return autoFireNotification;
	}
	public void setAutoFireNotification(Integer autoFireNotification) {
		this.autoFireNotification = autoFireNotification;
	}
	public Integer getHomeSecuritySystem() {
		return homeSecuritySystem;
	}
	public void setHomeSecuritySystem(Integer homeSecuritySystem) {
		this.homeSecuritySystem = homeSecuritySystem;
	}
	public String getSwimmingPool() {
		return swimmingPool;
	}
	public void setSwimmingPool(String swimmingPool) {
		this.swimmingPool = swimmingPool;
	}
	
	
}
