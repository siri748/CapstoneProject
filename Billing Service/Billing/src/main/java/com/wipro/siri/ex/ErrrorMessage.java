package com.wipro.siri.ex;


public class ErrrorMessage {

	private String message;
	private String status;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ErrrorMessage() {
		super();
	}
	public ErrrorMessage(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	
}
