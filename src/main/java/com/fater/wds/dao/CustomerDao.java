package com.fater.wds.dao;

import java.util.List;

import com.fater.wds.entity.Customer;

public interface CustomerDao {
	/**
	 * return all customers
	 * @return
	 */
	List<Customer> queryCustomer();
	
	/**
	 * 
	 */
	Customer queryByCustomerId(long customerId);
	
	int insertCustomer(Customer customer);
	
	int updateCustomer(Customer customer);
	
	int deleteCustomer(long customerId);
}
