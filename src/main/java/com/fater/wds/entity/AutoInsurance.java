package com.fater.wds.entity;

public class AutoInsurance extends Policy{
	private Long policyId;
	private String autoInsuranceType;
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public String getAutoInsuranceType() {
		return autoInsuranceType;
	}
	public void setAutoInsuranceType(String autoInsuranceType) {
		this.autoInsuranceType = autoInsuranceType;
	}
}
