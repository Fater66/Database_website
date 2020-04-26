package com.fater.wds.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.HomeInsurance;

public class HomeInsuranceDaoTest extends BaseTest{
	@Autowired
	private HomeInsuranceDao homeInsuranceDao;
	
	@Test
	public void testInsertHomeInsurance()
	{
		HomeInsurance homeInsurance = new HomeInsurance();
		homeInsurance.setHomeInsuranceType("H");
		homeInsurance.setPolicyId(1L);
		homeInsuranceDao.insertHomeInsurance(homeInsurance);
	}
}
