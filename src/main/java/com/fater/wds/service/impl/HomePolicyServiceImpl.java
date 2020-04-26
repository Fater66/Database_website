package com.fater.wds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.HomePolicyDao;
import com.fater.wds.dto.HomePolicyExecution;
import com.fater.wds.entity.HomePolicy;
import com.fater.wds.enums.HomePolicyStateEnum;
import com.fater.wds.exceptions.HomePolicyOperationException;
import com.fater.wds.service.HomePolicyService;

@Service
public class HomePolicyServiceImpl implements HomePolicyService{

	@Autowired
	private HomePolicyDao homePolicyDao;
	@Override
	@Transactional
	public HomePolicyExecution addHomePolicy(long homeId, long policyId) {
		int effectedNum;
		HomePolicy homePolicy = new HomePolicy();
		homePolicy.setHomeId(homeId);
		homePolicy.setPolicyId(policyId);;
		try {
			effectedNum = homePolicyDao.insertHomePolicy(homePolicy);
			if(effectedNum <=0)
			{
				throw new HomePolicyOperationException("fail to add homepolicy");
			}
		} catch (Exception e) {
			throw new HomePolicyOperationException("add homepolicy error"+e.getMessage());
		}
		return new HomePolicyExecution(HomePolicyStateEnum.SUCCESS,homePolicy);
	}

}
