package com.fater.wds.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.VehiclePolicy;

public class VehiclePolicyDaoTest extends BaseTest{
	@Autowired
	private VehiclePolicyDao vehiclePolicyDao;
	
	@Test
	public void testInsertVehiclePolicy()
	{
		VehiclePolicy vehiclePolicy = new VehiclePolicy();
		vehiclePolicy.setVehicleId(3L);
		vehiclePolicy.setPolicyId(11L);
		vehiclePolicyDao.insertVehiclePolicy(vehiclePolicy);
	}
}
