package com.travel.travtronics.response;

public class MessageStatusResponse {
	private int status;
	private String message;

	public MessageStatusResponse() {
	};

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageStatusResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

}
