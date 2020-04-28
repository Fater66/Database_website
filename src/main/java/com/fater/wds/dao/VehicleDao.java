package com.fater.wds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fater.wds.entity.Vehicle;

public interface VehicleDao {

	int insertVehicle(Vehicle vehicle);
	
	List<Vehicle> queryVehicleListByCustomerId(long customerId);
	
	List<Vehicle> queryVehicleListByPolicyId(long policyId);
	
	List<Vehicle> queryVehicleListByCondition(@Param("vehicleCondition")Vehicle vehicleCondition);
	
	Vehicle queryByVehicleId(long vehicleId);
	
	int updateVehicle(Vehicle vehicle);	
}
