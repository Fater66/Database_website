package com.fater.wds.service;

import java.sql.Date;
import java.util.List;

import com.fater.wds.dto.HomeExecution;
import com.fater.wds.entity.Home;

public interface HomeService {

	public HomeExecution addHome(Home home);
	
	public HomeExecution queryHomeByCustomerId(long customerId);
	
	public HomeExecution queryHomeByPolicyId(long policyId);
	
	public Home getByHomeId(long homeId);
	
	public HomeExecution modifyHome(Home home);
	
	public HomeExecution deleteHome(long homeId);
	
	public HomeExecution getHomeList(Home homeCondition,Date minDate,Date maxDate,Float minValue,Float maxValue,Float minArea,Float maxArea);
	
	public List<Home> getAllHomeList();
}
