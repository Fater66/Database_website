package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.HomePolicy;
import com.fater.wds.enums.HomePolicyStateEnum;

public class HomePolicyExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//homepolicy数量
	private int count;
	
	//操作的homepolicy（增删改的时候用到)
	private HomePolicy homePolicy;
	
	//表（查询列表的时候使用）
	private List<HomePolicy> homePolicyList;
	public HomePolicyExecution() {
		
	}
	//操作失败的构造器
	public HomePolicyExecution(HomePolicyStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
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
	public HomePolicy getHomePolicy() {
		return homePolicy;
	}
	public void setHomePolicy(HomePolicy homePolicy) {
		this.homePolicy = homePolicy;
	}
	public List<HomePolicy> getHomePolicyList() {
		return homePolicyList;
	}
	public void setHomePolicyList(List<HomePolicy> homePolicyList) {
		this.homePolicyList = homePolicyList;
	}
	//操作成功的构造器
	public HomePolicyExecution(HomePolicyStateEnum stateEnum,HomePolicy homePolicy)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.homePolicy = homePolicy;
	}
	//操作成功的构造器
	public HomePolicyExecution(HomePolicyStateEnum stateEnum,List<HomePolicy> homePolicyList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.homePolicyList = homePolicyList;
	}
}
