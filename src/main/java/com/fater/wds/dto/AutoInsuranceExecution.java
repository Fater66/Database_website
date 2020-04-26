package com.fater.wds.dto;

import java.util.List;

import com.fater.wds.entity.AutoInsurance;
import com.fater.wds.enums.AutoInsuranceStateEnum;

public class AutoInsuranceExecution {
	//结果状态
		private int state;
		
		//状态标示
		private String stateInfo;
		
		//autoinsurance数量
		private int count;
		
		//操作的autoinsurance（增删改autoinsurance的时候用到)
		private AutoInsurance autoInsurance;
		
		//autoinsurance表（查询policy列表的时候使用）
		private List<AutoInsurance> autoInsuranceList;
		public AutoInsuranceExecution() {
			
		}
		//autoinsurance操作失败的构造器
		public AutoInsuranceExecution(AutoInsuranceStateEnum stateEnum)
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
		public AutoInsurance getAutoInsurance() {
			return autoInsurance;
		}
		public void setAutoInsurance(AutoInsurance autoInsurance) {
			this.autoInsurance = autoInsurance;
		}
		public List<AutoInsurance> getAutoInsuranceList() {
			return autoInsuranceList;
		}
		public void setAutoInsuranceList(List<AutoInsurance> autoInsuranceList) {
			this.autoInsuranceList = autoInsuranceList;
		}
		//操作成功的构造器
		public AutoInsuranceExecution(AutoInsuranceStateEnum stateEnum,AutoInsurance autoInsurance)
		{
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.autoInsurance = autoInsurance;
		}
		//操作成功的构造器
		public AutoInsuranceExecution(AutoInsuranceStateEnum stateEnum,List<AutoInsurance> autoInsuranceList)
		{
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.autoInsuranceList = autoInsuranceList;
		}
}
