package com.fater.wds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.VehiclePolicyDao;
import com.fater.wds.dto.VehiclePolicyExecution;
import com.fater.wds.entity.VehiclePolicy;
import com.fater.wds.enums.VehiclePolicyStateEnum;
import com.fater.wds.exceptions.VehiclePolicyOperationException;
import com.fater.wds.service.VehiclePolicyService;

@Service
public class VehiclePolicyServiceImpl implements VehiclePolicyService{

	@Autowired
	private VehiclePolicyDao vehiclePolicyDao;
	@Override
	@Transactional
	public VehiclePolicyExecution addVehiclePolicy(long vehicleId, long policyId) {
		int effectedNum;
		VehiclePolicy vehiclePolicy = new VehiclePolicy();
		vehiclePolicy.setVehicleId(vehicleId);
		vehiclePolicy.setPolicyId(policyId);;
		try {
			effectedNum = vehiclePolicyDao.insertVehiclePolicy(vehiclePolicy);
			if(effectedNum <=0)
			{
				throw new VehiclePolicyOperationException("fail to add vehiclepolicy");
			}
		} catch (Exception e) {
			throw new VehiclePolicyOperationException("add vehiclepolicy error"+e.getMessage());
		}
		return new VehiclePolicyExecution(VehiclePolicyStateEnum.SUCCESS,vehiclePolicy);
	}

}
