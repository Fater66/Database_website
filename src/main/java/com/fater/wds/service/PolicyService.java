package com.fater.wds.service;

import com.fater.wds.dto.PolicyExecution;
import com.fater.wds.entity.Policy;

public interface PolicyService {
	PolicyExecution addPolicy(Policy policy);
	
	/**
	 * 根据policyConiditon返回相应列表数据
	 * @param policyCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PolicyExecution getPolicyList(Policy policyCondition);
	
	public PolicyExecution modifyPolicy(Policy policy);
	
	public Policy getByPolicyId(long policyId);
}
