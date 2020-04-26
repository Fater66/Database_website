package com.fater.wds.service;

import com.fater.wds.dto.AccountExecution;
import com.fater.wds.entity.Account;

public interface AccountService {
	AccountExecution addAccount(Account account);
	Account loginAccount(String accountUsername);
	Account getByAccountId(long accountId);
}
