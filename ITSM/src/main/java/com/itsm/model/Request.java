package com.itsm.model;



import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int requestId;
	private int burstTime;
	private LocalDateTime arrivalTime=LocalDateTime.now();
	private String priority;
	private String status = "raised";
	private String subject;
	private int actionOwnerId;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private User user;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(int requestId, int burstTime, LocalDateTime arrivalTime, String priority, String status, String subject,
			int actionOwnerId, User user) {
		super();
		this.requestId = requestId;
		this.burstTime = burstTime;
		this.arrivalTime = arrivalTime;
		this.priority = priority;
		this.status = status;
		this.subject = subject;
		this.actionOwnerId = actionOwnerId;
		this.user = user;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getActionOwnerId() {
		return actionOwnerId;
	}

	public void setActionOwnerId(int actionOwnerId) {
		this.actionOwnerId = actionOwnerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", burstTime=" + burstTime + ", arrivalTime=" + arrivalTime
				+ ", priority=" + priority + ", status=" + status + ", subject=" + subject + ", actionOwnerId="
				+ actionOwnerId + ", user=" + user + "]";
	}
}
