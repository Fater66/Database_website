package com.fater.wds.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Customer;
import com.fater.wds.entity.Policy;
import com.fater.wds.util.TimeCalculator;

public class PolicyDaoTest extends BaseTest{
	@Autowired
	private PolicyDao policyDao;
	
	@Test
//	@Ignore
	public void testQueryPolicyListAndCount()
	{
		Policy policyCondition = new Policy();
		Customer customer = new Customer();
		customer.setCustomerId(4L);
		policyCondition.setCustomer(customer);
		policyCondition.setStatus("P");
		List<Policy> policyList = policyDao.queryPolicyList(policyCondition);
//		int count = policyDao.queryPolicyCount(policyCondition);
//		assertEquals(count,5);
		System.out.println(policyList.size());
		for(int i =0;i<policyList.size();i++)
		{
			System.out.print(policyList.get(i).getPremiumAmount());
		}
	}
	
	@Test
	@Ignore
	public void testInsertPolicy()
	{
		Policy policy = new Policy();
		//创建测试用例
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		policy.setType("A");
		policy.setCustomer(customer);
		policy.setStartDate(Date.valueOf("2020-12-11"));
		policy.setEndDate(Date.valueOf("2020-12-11"));
		policy.setPremiumAmount(100f);
		policy.setStatus("C");
		//测试
		int effectedNum = policyDao.insertPolicy(policy);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdatePolicy()
	{
		Policy policy = new Policy();
		//因为是更新 需要指定id
		policy.setPolicyId(2L);
		//创建测试用例
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		policy.setType("A");
		policy.setCustomer(customer);
		policy.setStartDate(TimeCalculator.getPreDoneScore(Date.valueOf("2020-12-11"), -2));
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		policy.setEndDate(currentDate);
		policy.setPremiumAmount(1000f);
		policy.setStatus("P");
		//测试
		int effectedNum = policyDao.updatePolicy(policy);
		assertEquals(1,effectedNum);
	}
}
