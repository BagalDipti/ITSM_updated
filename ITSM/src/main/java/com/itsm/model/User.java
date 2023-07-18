package com.itsm.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	private String userName;

	private String userEmail;

	private long userMobileNo;
	private String role;
	
	
	private int managerId;
	
//	private String loginId;

	private String loginPassword;

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Request> requestList = new LinkedHashSet<>();


//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLES",
//            joinColumns = {
//            @JoinColumn(name = "USER_ID")
//            },
//            inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID") })
//    private Set<Role> roles;


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public long getUserMobileNo() {
		return userMobileNo;
	}


	public void setUserMobileNo(long userMobileNo) {
		this.userMobileNo = userMobileNo;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public String getLoginPassword() {
		return loginPassword;
	}


	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


	public Set<Request> getRequestList() {
		return requestList;
	}


	public void setRequestList(Set<Request> requestList) {
		this.requestList = requestList;
	}


	public User(int id, String userName, String userEmail, long userMobileNo, String role, int managerId,
			String loginPassword, Set<Request> requestList) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userMobileNo = userMobileNo;
		this.role = role;
		this.managerId = managerId;
		this.loginPassword = loginPassword;
		this.requestList = requestList;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userEmail=" + userEmail + ", userMobileNo="
				+ userMobileNo + ", role=" + role + ", managerId=" + managerId + ", loginPassword=" + loginPassword
				+ ", requestList=" + requestList + "]";
	}


	
	}


	

	
	
	
	

