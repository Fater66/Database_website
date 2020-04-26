package com.fater.wds.service;

import com.fater.wds.dto.HomeExecution;
import com.fater.wds.entity.Home;

public interface HomeService {

	public HomeExecution addHome(Home home);
	
	public HomeExecution queryHomeByCustomerId(long customerId);
	
	public HomeExecution queryHomeByPolicyId(long policyId);
	
	public Home getByHomeId(long homeId);
	
	public HomeExecution modifyHome(Home home);
}
