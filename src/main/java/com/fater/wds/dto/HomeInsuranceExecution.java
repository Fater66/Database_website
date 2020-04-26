package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.HomeInsurance;
import com.fater.wds.enums.HomeInsuranceStateEnum;

public class HomeInsuranceExecution {
	//结果状态
		private int state;
		
		//状态标示
		private String stateInfo;
		
		//customer数量
		private int count;
		
		//操作的customer（增删改customer的时候用到)
		private HomeInsurance homeInsurance;
		
		//customer表（查询policy列表的时候使用）
		private List<HomeInsurance> homeInsuranceList;
		public HomeInsuranceExecution() {
			
		}
		//customer操作失败的构造器
		public HomeInsuranceExecution(HomeInsuranceStateEnum stateEnum)
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
		public HomeInsurance getHomeInsurance() {
			return homeInsurance;
		}
		public void setHomeInsurance(HomeInsurance homeInsurance) {
			this.homeInsurance = homeInsurance;
		}
		public List<HomeInsurance> getHomeInsuranceList() {
			return homeInsuranceList;
		}
		public void setHomeInsuranceList(List<HomeInsurance> homeInsuranceList) {
			this.homeInsuranceList = homeInsuranceList;
		}
		//操作成功的构造器
		public HomeInsuranceExecution(HomeInsuranceStateEnum stateEnum,HomeInsurance homeInsurance)
		{
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.homeInsurance = homeInsurance;
		}
		//操作成功的构造器
		public HomeInsuranceExecution(HomeInsuranceStateEnum stateEnum,List<HomeInsurance> homeInsuranceList)
		{
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.homeInsuranceList = homeInsuranceList;
		}
}
