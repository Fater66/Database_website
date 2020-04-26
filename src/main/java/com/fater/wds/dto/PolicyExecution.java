package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Policy;
import com.fater.wds.enums.PolicyStateEnum;

public class PolicyExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//policy数量
	private int count;
	
	//操作的policy（增删改policy的时候用到)
	private Policy policy;
	
	//policy列表（查询policy列表的时候使用）
	private List<Policy> policyList;
	public PolicyExecution() {
		
	}
	//店铺操作失败的构造器
	public PolicyExecution(PolicyStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public PolicyExecution(PolicyStateEnum stateEnum,Policy policy)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.policy = policy;
	}
	//操作成功的构造器
	public PolicyExecution(PolicyStateEnum stateEnum,List<Policy> policyList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.policyList = policyList;
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
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public List<Policy> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Policy> policyList) {
		this.policyList = policyList;
	}
}
