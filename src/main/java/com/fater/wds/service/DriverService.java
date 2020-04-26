package com.fater.wds.service;

import com.fater.wds.dto.DriverExecution;
import com.fater.wds.entity.Driver;

public interface DriverService {

	public DriverExecution addDriver(Driver driver);

	public DriverExecution queryDriverByCustomerId(long customerId);
	
	public DriverExecution queryDriverByVehicleId(long vehicleId);

	public Driver getByDriverId(long driverId);

	public DriverExecution modifyDriver(Driver driver);

}
