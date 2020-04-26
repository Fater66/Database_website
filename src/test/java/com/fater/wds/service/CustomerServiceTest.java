package com.fater.wds.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Customer;

public class CustomerServiceTest extends BaseTest{
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testGetCustomerList()
	{
		List<Customer> customerList = customerService.getCustomerList();
		assertEquals("Jenny",customerList.get(0).getCustomerFirstName());
		for(int i = 0;i<customerList.size();i++)
		{
			System.out.println(customerList.get(i).getCustomerId());
		}
	}
}
