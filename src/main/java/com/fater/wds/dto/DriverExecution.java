package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Driver;
import com.fater.wds.enums.DriverStateEnum;

public class DriverExecution {
	// 结果状态
	private int state;
	// 状态标示
	private String stateInfo;
	// driver数量
	private int count;
	// 操作的driver（增删改driver的时候用到)
	private Driver driver;
	// driver列表（查询driver列表的时候使用）
	private List<Driver> driverList;

	public DriverExecution() {
	}

	// 店铺操作失败的构造器
	public DriverExecution(DriverStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的构造器
	public DriverExecution(DriverStateEnum stateEnum, Driver driver) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.driver = driver;
	}

	// 操作成功的构造器
	public DriverExecution(DriverStateEnum stateEnum, List<Driver> driverList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.driverList = driverList;
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public List<Driver> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<Driver> driverList) {
		this.driverList = driverList;
	}

}
