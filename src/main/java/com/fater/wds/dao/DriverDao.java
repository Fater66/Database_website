package com.fater.wds.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fater.wds.entity.Driver;

public interface DriverDao {

	int insertDriver(Driver driver);

	List<Driver> queryDriverListByCustomerId(long customerId);
	
	List<Driver> queryDriverListByVehicleId(long vehicleId);
	
	List<Driver> queryDriverListByCondition(@Param("driverCondition")Driver driverCondition,@Param("minDate")Date minDate,@Param("maxDate")Date maxDate);

	Driver queryByDriverId(long driverId);

	int updateDriver(Driver driver);
	
	List<Driver> queryAllDriver();
	
	int deleteDriver(long driverId);
}
