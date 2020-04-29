package com.fater.wds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.PolicyDao;
import com.fater.wds.dto.PolicyExecution;
import com.fater.wds.entity.Customer;
import com.fater.wds.entity.Policy;
import com.fater.wds.enums.PolicyStateEnum;
import com.fater.wds.exceptions.PolicyOperationException;
import com.fater.wds.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService{

	@Autowired
	private PolicyDao policyDao;
	
	@Override
	@Transactional
	public PolicyExecution addPolicy(Policy policy) {
		if(policy==null)
		{
			return new PolicyExecution(PolicyStateEnum.NULL_POLICY);
		}
		try {
			int effectedNum = policyDao.insertPolicy(policy);
			if(effectedNum<0)
			{
				throw new PolicyOperationException("fail to add policy!");
			}
			
		}catch(Exception e)
		{
			throw new PolicyOperationException("addPolicy error:" + e.getMessage());
		}
		// no error return policy
		return new PolicyExecution(PolicyStateEnum.CHECK,policy);
	}

	@Override
	public List<Policy> getAllPolicyList()
	{
		return policyDao.queryAllPolicy();
	}
	
	@Override
	public PolicyExecution getPolicyList(Policy policyCondition) {
		List<Policy> policyList=policyDao.queryPolicyList(policyCondition);
		int count = policyDao.queryPolicyCount(policyCondition);
		PolicyExecution pe = new PolicyExecution();
		if(policyList != null)
		{
			pe.setPolicyList(policyList);
			pe.setCount(count);
		}else
		{
			pe.setState(PolicyStateEnum.INNER_ERROR.getState());
		}
		return pe;
	}

	@Override
	//加了个transactional
	@Transactional
	public PolicyExecution modifyPolicy(Policy policy) throws PolicyOperationException{
		if(policy == null || policy.getPolicyId()==null)
		{
			return new PolicyExecution(PolicyStateEnum.NULL_POLICY);
		}
		else
		{
			try {
				int effectedNum = policyDao.updatePolicy(policy);
				if(effectedNum <= 0)
				{
					return new PolicyExecution(PolicyStateEnum.INNER_ERROR);
				}
				else
				{
					policy = policyDao.queryByPolicyId(policy.getPolicyId());
					return new PolicyExecution(PolicyStateEnum.SUCCESS,policy);
				}
			}catch(Exception e)
			{
				throw new PolicyOperationException("modifyPolicy error" + e.getMessage());
			}
		}
	}

	@Override
	public Policy getByPolicyId(long policyId) {
		return policyDao.queryByPolicyId(policyId);
	}

}
