package com.fater.wds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.AutoInsuranceDao;
import com.fater.wds.dto.AutoInsuranceExecution;
import com.fater.wds.entity.AutoInsurance;
import com.fater.wds.enums.AutoInsuranceStateEnum;
import com.fater.wds.exceptions.AutoInsuranceOperationException;
import com.fater.wds.service.AutoInsuranceService;

@Service
public class AutoInsuranceServiceImpl implements AutoInsuranceService{

	@Autowired
	private AutoInsuranceDao autoInsuranceDao;
	
	@Override
	@Transactional
	public AutoInsuranceExecution addAutoInsurance(AutoInsurance autoInsurance) {
		if(autoInsurance == null)
		{
			return new AutoInsuranceExecution(AutoInsuranceStateEnum.NULL_AUTOINSURANCE);
		}
		int effectedNum;
		try {
			effectedNum = autoInsuranceDao.insertAutoInsurance(autoInsurance);
			if(effectedNum <=0)
			{
				throw new AutoInsuranceOperationException("fail to add autoinsurance");
			}
		} catch (Exception e) {
			throw new AutoInsuranceOperationException("add autoinsurance error"+e.getMessage());
		}
		return new AutoInsuranceExecution(AutoInsuranceStateEnum.SUCCESS,autoInsurance);
	}

}
