package com.travel.travtronics.enums;

public enum Status {

	Active("1"), InActive("0");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
