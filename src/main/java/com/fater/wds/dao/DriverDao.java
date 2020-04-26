package com.fater.wds.dao;

import java.util.List;

import com.fater.wds.entity.Driver;

public interface DriverDao {

	int insertDriver(Driver driver);

	List<Driver> queryDriverListByCustomerId(long customerId);
	
	List<Driver> queryDriverListByVehicleId(long vehicleId);

	Driver queryByDriverId(long driverId);

	int updateDriver(Driver driver);
}
