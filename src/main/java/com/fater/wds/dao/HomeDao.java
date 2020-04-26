package com.fater.wds.dao;

import java.util.List;

import com.fater.wds.entity.Home;

public interface HomeDao {

	int insertHome(Home home);
	
	List<Home> queryHomeListByCustomerId(long customerId);
	
	List<Home> queryHomeListByPolicyId(long policyId);
	
	Home queryByHomeId(long homeId);
	
	int updateHome(Home home);
}
