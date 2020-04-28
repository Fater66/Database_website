package com.fater.wds.dao;

import java.sql.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Home;

public class HomeDaoTest extends BaseTest{
	@Autowired
	private HomeDao homeDao;
	
	@Test
	@Ignore
	public void testInsertHome()
	{
		Home home = new Home();
		home.setHomePurchaseDate(Date.valueOf("2020-12-11"));
		home.setHomePurchaseValue(1000f);
		home.setHomeArea(66f);
		home.setHomeType("M");
		home.setAutoFireNotification(0);
		home.setHomeSecuritySystem(1);
		home.setSwimmingPool(null);
		home.setBasement(0);
		homeDao.insertHome(home);
	}
	
	@Test
	@Ignore
	public void testQueryHomeListByCustomerId()
	{
		long customerId = 1;
		List<Home> homeList = homeDao.queryHomeListByCustomerId(customerId);
		System.out.println(homeList.size());
	}
	
	@Test
	@Ignore
	public void testQueryHomeListByPolicyId()
	{
		long policyId = 28;
		List<Home> homeList = homeDao.queryHomeListByPolicyId(policyId);
		System.out.println(homeList.size());
	}
	
	@Test
	public void testQueryHomeListByCondition()
	{
		Home homeCondition = new Home();
		homeCondition.setCustomerId(4L);
		List<Home> homeList = homeDao.queryHomeListByCondition(homeCondition, null, null, null, null, 800F,900f);
		for(int i =0;i<homeList.size();i++)
		{
			System.out.println(homeList.get(i).getHomeArea());
		}
		
	}
}
