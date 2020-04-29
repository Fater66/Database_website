package com.fater.wds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fater.wds.dao.CustomerDao;
import com.fater.wds.dto.CustomerExecution;
import com.fater.wds.entity.Customer;
import com.fater.wds.enums.CustomerStateEnum;
import com.fater.wds.exceptions.CustomerOperationException;
import com.fater.wds.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<Customer> getCustomerList()
	{
		return customerDao.queryCustomer();
	}

	@Override
	@Transactional
	public CustomerExecution addCustomer(Customer customer) {
		if(customer == null)
		{
			return new CustomerExecution(CustomerStateEnum.NULL_CUSTOMER);
		}
		try
		{
			int effectedNum = customerDao.insertCustomer(customer);
			if(effectedNum <= 0)
			{
				throw new CustomerOperationException("fail to add customer");
			}
		}catch (Exception e)
		{
			throw new CustomerOperationException("add customer error" + e.getMessage());
		}
		return new CustomerExecution(CustomerStateEnum.CHECK,customer);
	}

	@Override
	public Customer getByCustomerId(long customerId) {
		return customerDao.queryByCustomerId(customerId);
	}

	@Override
	public CustomerExecution modifyCustomer(Customer customer) {
		if(customer ==null)
		{
			return new CustomerExecution(CustomerStateEnum.NULL_CUSTOMER);
		}
		else
		{
			try {
				int effectedNum = customerDao.updateCustomer(customer);
				if(effectedNum <=0)
				{
					return new CustomerExecution(CustomerStateEnum.INNER_ERROR);
				}
				else
				{
					customer = customerDao.queryByCustomerId(customer.getCustomerId());
					return new CustomerExecution(CustomerStateEnum.SUCCESS,customer);
				}
			}catch(Exception e)
			{
				throw new CustomerOperationException("Modify customer error" + e.getMessage());
			}
		}
	}
	
	@Override
	public CustomerExecution deleteCustomer(long customerId) {
		try {
			int effectedNum = customerDao.deleteCustomer(customerId);
			if(effectedNum <=0)
			{
				return new CustomerExecution(CustomerStateEnum.INNER_ERROR);
			}
			else
			{
				return new CustomerExecution(CustomerStateEnum.SUCCESS);
			}
		}catch(Exception e)
		{
			throw new CustomerOperationException("Delete customer error" + e.getMessage());
		}

	}
}
