package com.fater.wds.service;

import java.sql.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.dto.PolicyExecution;
import com.fater.wds.entity.Customer;
import com.fater.wds.entity.Policy;

public class PolicyServiceTest extends BaseTest{
	@Autowired
	private PolicyService policyService;
	
	@Test
	@Ignore
	public void testGetPolicyList()
	{
		Policy policyCondition = new Policy();
		Customer customer = new Customer();
		customer.setCustomerId(4L);
		policyCondition.setCustomer(customer);
		
		PolicyExecution pe= policyService.getPolicyList(policyCondition, 2, 5);
		System.out.println("policy list size is" +pe.getPolicyList().size());
		System.out.println("policy amount is " + pe.getCount());
		System.out.println(pe.getPolicyList().get(0).getStartDate());
	}
	@Test
	@Ignore
	public void testAddPolicy()
	{
		Policy policy = new Policy();
		//创建测试用例
		Customer customer = new Customer();
		customer.setCustomerId(2L);
		policy.setType("A");
		policy.setCustomer(customer);
		policy.setStartDate(Date.valueOf("2111-1-1"));
		policy.setEndDate(Date.valueOf("2110-1-1"));
		policy.setPremiumAmount(123f);
		policy.setStatus("C");
		//测试
		System.out.println(policy.getPolicyId());
		PolicyExecution pe = policyService.addPolicy(policy);
		System.out.println(policy.getPolicyId());
		System.out.println(pe.getState());
	}
	
	@Test
	@Ignore
	public void testModifyPolicy()
	{
		Policy policy = new Policy();
		policy.setPolicyId(3L);
		policy.setPremiumAmount(3231f);
		policy.setStatus("P");
		Customer customer = new Customer();
		customer.setCustomerId(5L);
		policy.setCustomer(customer);
		policy.setEndDate(Date.valueOf("2010-2-3"));
		policyService.modifyPolicy(policy);
	}
	
	@Test
	@Ignore
	public void testQueryByPolicyId()
	{
		Policy policy = policyService.getByPolicyId(3L);
		System.out.println(policy.getCustomer().getCustomerFirstName());
	}
}
