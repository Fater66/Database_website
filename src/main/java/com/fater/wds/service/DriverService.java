package com.fater.wds.service;

import java.sql.Date;
import java.util.List;

import com.fater.wds.dto.DriverExecution;
import com.fater.wds.entity.Driver;

public interface DriverService {

	public DriverExecution addDriver(Driver driver);

	public DriverExecution queryDriverByCustomerId(long customerId);
	
	public DriverExecution queryDriverByVehicleId(long vehicleId);

	public Driver getByDriverId(long driverId);

	public DriverExecution modifyDriver(Driver driver);
	
	public DriverExecution getDriverList(Driver driverCondition,Date minDate,Date maxDate);
	
	public List<Driver> getAllDriverList();
	
	public DriverExecution deleteDriver(long driverId);

}
