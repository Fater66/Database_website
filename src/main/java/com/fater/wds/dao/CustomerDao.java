package com.fater.wds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fater.wds.entity.Customer;

public interface CustomerDao {
	/**
	 * return all customers
	 * @return
	 */
	List<Customer> queryCustomer();
	
	List<Customer> queryCustomerListByCondition(@Param("customerCondition") Customer customerCondition);
	/**
	 * 
	 */
	Customer queryByCustomerId(long customerId);
	
	int insertCustomer(Customer customer);
	
	int updateCustomer(Customer customer);
	
	int deleteCustomer(long customerId);
}
