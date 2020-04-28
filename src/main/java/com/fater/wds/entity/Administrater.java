package com.fater.wds.entity;

public class Administrater {
	private Long administraterId;
	private String username;
	private String password;
	
	public Long getAdminId() {
		return administraterId;
	}
	public void setAdminId(Long adminId) {
		this.administraterId = adminId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
