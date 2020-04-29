package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Administrater;
import com.fater.wds.enums.AdministraterStateEnum;

public class AdministraterExecution {
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

	public Administrater getAdministrater() {
		return administrater;
	}

	public void setAdministrater(Administrater administrater) {
		this.administrater = administrater;
	}

	public List<Administrater> getAdministraterList() {
		return administraterList;
	}

	public void setAdministraterList(List<Administrater> administraterList) {
		this.administraterList = administraterList;
	}
	//policy数量
	private int count;
	
	//操作的policy（增删改policy的时候用到)
	private Administrater administrater;
	
	//policy列表（查询policy列表的时候使用）
	private List<Administrater> administraterList;
	public AdministraterExecution() {
		
	}
	
	//店铺操作失败的构造器
	public AdministraterExecution(AdministraterStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public AdministraterExecution(AdministraterStateEnum stateEnum,Administrater administrater)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.administrater= administrater;
	}
	//操作成功的构造器
	public AdministraterExecution(AdministraterStateEnum stateEnum,List<Administrater> administraterList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.administraterList = administraterList;
	}
}
