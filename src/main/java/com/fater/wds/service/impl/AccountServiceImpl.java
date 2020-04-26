package com.fater.wds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fater.wds.dao.AccountDao;
import com.fater.wds.dto.AccountExecution;
import com.fater.wds.entity.Account;
import com.fater.wds.enums.AccountStateEnum;
import com.fater.wds.exceptions.AccountOperationException;
import com.fater.wds.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	@Override
	public AccountExecution addAccount(Account account) {
		if(account==null)
		{
			return new AccountExecution(AccountStateEnum.NULL_ACCOUNT);
		}
		try
		{
			int effectedNum = accountDao.insertAccount(account);
			if(effectedNum < 0)
			{
				throw new AccountOperationException("fail to add account");
			}
		}catch (Exception e)
		{
			throw new AccountOperationException("add account error" + e.getMessage());
		}
		return new AccountExecution(AccountStateEnum.CHECK,account);
	}
	
	@Override
	public Account getByAccountId(long accountId) {
		return accountDao.queryByAccountId(accountId);
	}

	@Override
	public Account loginAccount(String accountUsername) {
		return accountDao.queryByAccountUsername(accountUsername);
	}
	
}
