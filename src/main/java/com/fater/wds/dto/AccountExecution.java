package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Account;
import com.fater.wds.enums.AccountStateEnum;

public class AccountExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	//policy数量
	private int count;
	
	//操作的policy（增删改policy的时候用到)
	private Account account;
	
	//policy列表（查询policy列表的时候使用）
	private List<Account> accountList;
	public AccountExecution() {
		
	}
	
	//店铺操作失败的构造器
	public AccountExecution(AccountStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public AccountExecution(AccountStateEnum stateEnum,Account account)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.account= account;
	}
	//操作成功的构造器
	public AccountExecution(AccountStateEnum stateEnum,List<Account> accountList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.accountList = accountList;
	}
}
