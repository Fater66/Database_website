package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.VehiclePolicy;
import com.fater.wds.enums.VehiclePolicyStateEnum;

public class VehiclePolicyExecution {
	//结果状态
	private int state;
	
	//状态标示
	private String stateInfo;
	
	//vehiclepolicy数量
	private int count;
	
	//操作的vehiclepolicy（增删改的时候用到)
	private VehiclePolicy vehiclePolicy;
	
	//表（查询列表的时候使用）
	private List<VehiclePolicy> vehiclePolicyList;
	public VehiclePolicyExecution() {
		
	}
	//操作失败的构造器
	public VehiclePolicyExecution(VehiclePolicyStateEnum stateEnum)
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
	public VehiclePolicy getVehiclePolicy() {
		return vehiclePolicy;
	}
	public void setVehiclePolicy(VehiclePolicy vehiclePolicy) {
		this.vehiclePolicy = vehiclePolicy;
	}
	public List<VehiclePolicy> getVehiclePolicyList() {
		return vehiclePolicyList;
	}
	public void setVehiclePolicyList(List<VehiclePolicy> vehiclePolicyList) {
		this.vehiclePolicyList = vehiclePolicyList;
	}
	//操作成功的构造器
	public VehiclePolicyExecution(VehiclePolicyStateEnum stateEnum,VehiclePolicy vehiclePolicy)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.vehiclePolicy = vehiclePolicy;
	}
	//操作成功的构造器
	public VehiclePolicyExecution(VehiclePolicyStateEnum stateEnum,List<VehiclePolicy> vehiclePolicyList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.vehiclePolicyList = vehiclePolicyList;
	}
}
