package com.fater.wds.dao;

import com.fater.wds.entity.Account;

public interface AccountDao {
	
	Account queryByAccountId(long accountId);
	
	int insertAccount(Account account);
	
	Account queryByAccountUsername(String username);
}
