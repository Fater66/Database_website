package com.fater.wds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.HomeInsuranceDao;
import com.fater.wds.dto.HomeInsuranceExecution;
import com.fater.wds.entity.HomeInsurance;
import com.fater.wds.enums.HomeInsuranceStateEnum;
import com.fater.wds.exceptions.HomeInsuranceOperationException;
import com.fater.wds.service.HomeInsuranceService;

@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService{

	@Autowired
	private HomeInsuranceDao homeInsuranceDao;
	
	@Override
	@Transactional
	public HomeInsuranceExecution addHomeInsurance(HomeInsurance homeInsurance) {
		if(homeInsurance == null)
		{
			return new HomeInsuranceExecution(HomeInsuranceStateEnum.NULL_HOMEINSURANCE);
		}
		int effectedNum;
		try {
			effectedNum = homeInsuranceDao.insertHomeInsurance(homeInsurance);
			if(effectedNum <=0)
			{
				throw new HomeInsuranceOperationException("fail to add homeinsurance");
			}
		} catch (Exception e) {
			throw new HomeInsuranceOperationException("add homeinsurance error"+e.getMessage());
		}
		return new HomeInsuranceExecution(HomeInsuranceStateEnum.SUCCESS,homeInsurance);
	}

}
