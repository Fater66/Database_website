package com.fater.wds.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.Customer;

public class AccountServiceTest extends BaseTest{
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testAddAccount()
	{
		Account account = new Account();
		Customer customer = new Customer();
		customer.setCustomerId(2L);
		account.setCustomer(customer);
		account.setUsername("fyt");
		account.setPassword("131234");
		accountService.addAccount(account);
	}
}
