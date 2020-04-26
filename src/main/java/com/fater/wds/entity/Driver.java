package com.fater.wds.entity;

import java.sql.Date;

public class Driver {
	private Long driverId;
	private String driverLicenseId;
	private String driverLastName;
	private String driverFirstName;
	private Date driverBirthDate;
	private Long vehicleId;
	private Long customerId;
	
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public String getDriverLicenseId() {
		return driverLicenseId;
	}
	public void setDriverLicenseId(String driverLicenseId) {
		this.driverLicenseId = driverLicenseId;
	}
	public String getDriverLastName() {
		return driverLastName;
	}
	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}
	public String getDriverFirstName() {
		return driverFirstName;
	}
	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}
	public Date getDriverBirthDate() {
		return driverBirthDate;
	}
	public void setDriverBirthDate(Date driverBirthDate) {
		this.driverBirthDate = driverBirthDate;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
}
