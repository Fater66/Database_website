package com.fater.wds.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fater.wds.BaseTest;
import com.fater.wds.entity.Account;
import com.fater.wds.entity.Customer;

public class AccountDaoTest extends BaseTest{
	@Autowired
	private AccountDao accountDao;
	
	@Test
	@Ignore
	public void testQueryByAccountUsername()
	{
		String username = "asdfg";
		Account account = accountDao.queryByAccountUsername(username);
		System.out.println(account.getPassword());
	}
	
	@Test
	@Ignore
	public void testQueryByAccountId()
	{
		long accountId = 5;
		Account account = accountDao.queryByAccountId(accountId);
		System.out.println(account.getCustomer().getCustomerFirstName());
	}
	
	@Test
	@Ignore
	public void testInsertAccount()
	{
		Account account = new Account();
		Customer customer = new Customer();
		customer.setCustomerId(6L);
		account.setCustomer(customer);
		account.setUsername("主");
		account.setPassword("231412");
		System.out.println("插入前主键为："+account.getAccountId());
		int effectedNum = accountDao.insertAccount(account);
		System.out.println("插入后主键为："+account.getAccountId());
		assertEquals(1,effectedNum);
	}
}
