package com.itsm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String adminRole = "ADMIN";
	private String loginId = "Admin";
	private String loginPassword = "Admin";
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String adminRole, String loginId, String loginPassword) {
		super();
		this.id = id;
		this.adminRole = adminRole;
		this.loginId = loginId;
		this.loginPassword = loginPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminRole=" + adminRole + ", loginId=" + loginId + ", loginPassword="
				+ loginPassword + "]";
	}
	
	

	
}
