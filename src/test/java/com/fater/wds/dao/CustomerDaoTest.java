package com.fater.wds.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Customer;

public class CustomerDaoTest extends BaseTest{
	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void testQueryCustomerById()
	{
		long customerId = 2;
		Customer customer = customerDao.queryByCustomerId(customerId);
		System.out.println(customer.getCustomerLastName());
		
	}
	@Test
	@Ignore
	public void testQueryCustomerDao()
	{
		List<Customer> customerList = customerDao.queryCustomer();
		assertEquals(11,customerList.size());
		for(int i = 0;i<customerList.size();i++)
		{
			//print first name for all customers
			System.out.println(customerList.get(i).getCustomerFirstName());
		}
	}
	
	@Test
	@Ignore
	public void testInsertCustomer()
	{
		Customer customer = new Customer();
		customer.setCustomerLastName("test");
		customer.setCustomerFirstName("test");
		customer.setCustomerStreetAddress("test");
		customer.setCustomerCity("");
		customer.setCustomerState("");
		customer.setCustomerZipcode("");
		customer.setCustomerMaritalStatus("M");
		customer.setCustomerGender("M");
		System.out.println(customer.getCustomerId());
		int effectedNum = customerDao.insertCustomer(customer);
		assertEquals(1,effectedNum);
		System.out.println(customer.getCustomerId());
	}
}
