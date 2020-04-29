package com.fater.wds.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Vehicle;

public class VehicleDaoTest extends BaseTest{
	@Autowired
	private VehicleDao vehicleDao;
	
	
	
	@Test
	public void testQueryVehicleListByCondition()
	{
		Vehicle vehicleCondition = new Vehicle();
		vehicleCondition.setCustomerId(4L);
//		vehicleCondition.setVehicleMmy("BMW");
		vehicleCondition.setVehicleVin("HGB");
		List<Vehicle> vehicleList = vehicleDao.queryVehicleListByCondition(vehicleCondition);
		for(int i =0;i<vehicleList.size();i++)
		{
			System.out.println(vehicleList.get(i).getVehicleMmy());
		}
		
	}
}
