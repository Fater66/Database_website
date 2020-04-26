package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.Vehicle;
import com.fater.wds.enums.VehicleStateEnum;

public class VehicleExecution {
	//结果状态
	private int state;
	//状态标示
	private String stateInfo;
	//vehicle数量
	private int count;
	//操作的vehicle（增删改vehicle的时候用到)
	private Vehicle vehicle;
	//vehicle列表（查询vehicle列表的时候使用）
	private List<Vehicle> vehicleList;
	public VehicleExecution() {
	}
	//店铺操作失败的构造器
	public VehicleExecution(VehicleStateEnum stateEnum)
	{
	this.state = stateEnum.getState();
	this.stateInfo = stateEnum.getStateInfo();
	}
	//操作成功的构造器
	public VehicleExecution(VehicleStateEnum stateEnum,Vehicle vehicle)
	{
	this.state = stateEnum.getState();
	this.stateInfo = stateEnum.getStateInfo();
	this.vehicle = vehicle;
	}
	//操作成功的构造器
	public VehicleExecution(VehicleStateEnum stateEnum,List<Vehicle> vehicleList)
	{
	this.state = stateEnum.getState();
	this.stateInfo = stateEnum.getStateInfo();
	this.vehicleList = vehicleList;
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
	public Vehicle getVehicle() {
	return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
	}
	public List<Vehicle> getVehicleList() {
	return vehicleList;
	}
	public void setVehicleList(List<Vehicle> vehicleList) {
	this.vehicleList = vehicleList;
	}

}
