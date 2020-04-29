package com.fater.wds.entity;

public class Administrater {
	private Long administraterId;
	private String username;
	private String password;
	
	public Long getAdministraterId() {
		return administraterId;
	}
	public void setAdministraterId(Long administraterId) {
		this.administraterId = administraterId;
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
