package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Home;
import com.fater.wds.enums.HomeStateEnum;

public class HomeExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//home数量
	private int count;
	
	//操作的home（增删改home的时候用到)
	private Home home;
	
	//home列表（查询home列表的时候使用）
	private List<Home> homeList;
	public HomeExecution() {
		
	}
	//店铺操作失败的构造器
	public HomeExecution(HomeStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public HomeExecution(HomeStateEnum stateEnum,Home home)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.home = home;
	}
	//操作成功的构造器
	public HomeExecution(HomeStateEnum stateEnum,List<Home> homeList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.homeList = homeList;
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
	public Home getHome() {
		return home;
	}
	public void setHome(Home home) {
		this.home = home;
	}
	public List<Home> getHomeList() {
		return homeList;
	}
	public void setHomeList(List<Home> homeList) {
		this.homeList = homeList;
	}
	
	
}
