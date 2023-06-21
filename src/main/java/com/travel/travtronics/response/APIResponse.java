package com.travel.travtronics.response;

import java.util.Collections;
import java.util.List;


public class APIResponse {
	private String message;
	private int status;

	private List<?> data;
	private List<?> errors;

	public APIResponse() {
	};

	public APIResponse(int status, String message, List<?> data) {
		this(status, message, data, Collections.emptyList());
	}

	public APIResponse(Integer status, String message, List<?> data, List<?> errors) {
		this.message = message;
		this.status = status;
		this.data = data;
		this.errors = errors;
	}

	public APIResponse(int status, String message) {
		this(status, message, Collections.emptyList(), Collections.emptyList());
	}

	public APIResponse(Integer status, String message, List<?> data) {
		this(status, message, data, Collections.emptyList());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public List<?> getErrors() {
		return errors;
	}

	public void setErrors(List<?> errors) {
		this.errors = errors;
	}

}