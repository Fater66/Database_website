package com.fater.wds.dao;

import java.util.List;

import com.fater.wds.entity.Vehicle;

public interface VehicleDao {

	int insertVehicle(Vehicle vehicle);
	
	List<Vehicle> queryVehicleListByCustomerId(long customerId);
	
	List<Vehicle> queryVehicleListByPolicyId(long policyId);
	
	Vehicle queryByVehicleId(long vehicleId);
	
	int updateVehicle(Vehicle vehicle);	
}
