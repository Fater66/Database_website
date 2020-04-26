package com.fater.wds.service;

import com.fater.wds.dto.VehicleExecution;
import com.fater.wds.entity.Vehicle;

public interface VehicleService {

	public VehicleExecution addVehicle(Vehicle vehicle);
	
	public VehicleExecution queryVehicleByCustomerId(long customerId);
	
	public VehicleExecution queryVehicleByPolicyId(long policyId);
	
	public Vehicle getByVehicleId(long vehicleId);
	
	public VehicleExecution modifyVehicle(Vehicle vehicle);

}
