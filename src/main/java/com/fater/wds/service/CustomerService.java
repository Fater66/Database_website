package com.fater.wds.service;

import java.util.List;

import com.fater.wds.dto.CustomerExecution;
import com.fater.wds.entity.Customer;

public interface CustomerService {
	List<Customer> getCustomerList();
	
	public CustomerExecution addCustomer(Customer customer);
	
	public Customer getByCustomerId(long customerId);
	
	public CustomerExecution modifyCustomer(Customer customer);
}
