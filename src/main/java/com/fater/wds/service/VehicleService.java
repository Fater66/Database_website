package com.fater.wds.service;

import java.util.List;

import com.fater.wds.dto.VehicleExecution;
import com.fater.wds.entity.Vehicle;

public interface VehicleService {

	public VehicleExecution addVehicle(Vehicle vehicle);
	
	public VehicleExecution queryVehicleByCustomerId(long customerId);
	
	public VehicleExecution queryVehicleByPolicyId(long policyId);
	
	public Vehicle getByVehicleId(long vehicleId);
	
	public VehicleExecution modifyVehicle(Vehicle vehicle);
	
	public VehicleExecution getVehicleList(Vehicle vehicleCondition);

	public List<Vehicle> getAllVehicleList();
	
	public VehicleExecution deleteVehicle(long vehicleId);
}
