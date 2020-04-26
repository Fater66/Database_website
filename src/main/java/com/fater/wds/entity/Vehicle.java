package com.fater.wds.entity;

public class Vehicle {
	private Long vehicleId;
	private String vehicleVin;
	private String vehicleMmy;
	private String vehicleStatus;
	private Long customerId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleVin() {
		return vehicleVin;
	}
	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}
	public String getVehicleMmy() {
		return vehicleMmy;
	}
	public void setVehicleMmy(String vehicleMmy) {
		this.vehicleMmy = vehicleMmy;
	}
	public String getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	
}
