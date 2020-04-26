package com.fater.wds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.VehicleDao;
import com.fater.wds.dto.VehicleExecution;
import com.fater.wds.entity.Vehicle;
import com.fater.wds.enums.VehicleStateEnum;
import com.fater.wds.exceptions.VehicleOperationException;
import com.fater.wds.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	@Override
	@Transactional
	public VehicleExecution addVehicle(Vehicle vehicle) {
		if (vehicle == null) {
			return new VehicleExecution(VehicleStateEnum.NULL_VEHICLE);
		}
		try {
			int effectedNum = vehicleDao.insertVehicle(vehicle);
			if (effectedNum <= 0) {
				throw new VehicleOperationException("fail to add vehicle");
			}
		} catch (Exception e) {
			throw new VehicleOperationException("add vehicle error" + e.getMessage());
		}
		return new VehicleExecution(VehicleStateEnum.SUCCESS, vehicle);
	}

	@Override
	public VehicleExecution queryVehicleByCustomerId(long customerId) {
		List<Vehicle> vehicleList = vehicleDao.queryVehicleListByCustomerId(customerId);
		VehicleExecution he = new VehicleExecution();
		if (vehicleList != null) {
			he.setVehicleList(vehicleList);
			he.setCount(vehicleList.size());
		} else {
			he.setState(VehicleStateEnum.INNER_ERROR.getState());
		}
		return he;
	}

	@Override
	public Vehicle getByVehicleId(long vehicleId) {
		return vehicleDao.queryByVehicleId(vehicleId);
	}

	@Override
	public VehicleExecution modifyVehicle(Vehicle vehicle) {
		if (vehicle == null) {
			return new VehicleExecution(VehicleStateEnum.NULL_VEHICLE);
		} else {
			try {
				int effectedNum = vehicleDao.updateVehicle(vehicle);
				if (effectedNum <= 0) {
					return new VehicleExecution(VehicleStateEnum.INNER_ERROR);
				} else {
					vehicle = vehicleDao.queryByVehicleId(vehicle.getVehicleId());
					return new VehicleExecution(VehicleStateEnum.SUCCESS, vehicle);
				}
			} catch (Exception e) {
				throw new VehicleOperationException("Modify vehicle error" + e.getMessage());
			}
		}
	}

	@Override
	public VehicleExecution queryVehicleByPolicyId(long policyId) {
		List<Vehicle> vehicleList=vehicleDao.queryVehicleListByPolicyId(policyId);
		VehicleExecution he = new VehicleExecution();
		if(vehicleList != null)
		{
			he.setVehicleList(vehicleList);
			he.setCount(vehicleList.size());
		}else
		{
			he.setState(VehicleStateEnum.INNER_ERROR.getState());
		}
		return he;
	}
}
