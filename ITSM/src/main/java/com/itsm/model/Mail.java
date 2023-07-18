package com.itsm.model;

import java.util.List;

public class Mail {
	private List<String> recipient;
    private int requestId;
    private String status; 
    private String subject;
    
    public Mail() {
    }

	public Mail(List<String> recipient, int requestId, String status, String subject) {
		super();
		this.recipient = recipient;
		this.requestId = requestId;
		this.status = status;
		this.subject = subject;
	}

	public List<String> getRecipient() {
		return recipient;
	}

	public void setRecipient(List<String> recipient) {
		this.recipient = recipient;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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

	@Override
	public String toString() {
		return "Mail [recipient=" + recipient + ", requestId=" + requestId + ", status=" + status + ", subject="
				+ subject + "]";
	}

	
    
}
