package com.fater.wds.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.HomePolicy;

public class HomePolicyDaoTest extends BaseTest{
	@Autowired
	private HomePolicyDao homePolicyDao;
	
	@Test
	public void testInsertHomePolicy()
	{
		HomePolicy homePolicy = new HomePolicy();
		homePolicy.setHomeId(2L);
		homePolicy.setPolicyId(9L);
		homePolicyDao.insertHomePolicy(homePolicy);
	}
}
