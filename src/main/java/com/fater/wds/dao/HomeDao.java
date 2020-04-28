package com.fater.wds.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fater.wds.entity.Home;

public interface HomeDao {

	int insertHome(Home home);
	
	List<Home> queryHomeListByCustomerId(long customerId);
	
	List<Home> queryHomeListByPolicyId(long policyId);
	
	List<Home> queryHomeListByCondition(@Param("homeCondition") Home homeCondition,@Param("minDate")Date minDate,@Param("maxDate")Date maxDate,
			@Param("minValue")Float minValue,@Param("maxValue")Float maxValue,@Param("minArea")Float minArea,@Param("maxArea")Float maxArea);
	
	Home queryByHomeId(long homeId);
	
	int updateHome(Home home);
}
