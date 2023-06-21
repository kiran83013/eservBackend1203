package com.travel.travtronics.response;

import java.util.List;

public class TrasitionResponseModel {

	private Long transitionId;

	private Integer fromStatusId;
	
	private String fromStatus;
	
    private String toStatus;
    private Integer toStatusId;
	
	private List<TrasitionResponseModel>  transitionDto;

	public Long getTransitionId() {
		return transitionId;
	}

	public void setTransitionId(Long transitionId) {
		this.transitionId = transitionId;
	}

	public Integer getFromStatusId() {
		return fromStatusId;
	}

	public void setFromStatusId(Integer fromStatusId) {
		this.fromStatusId = fromStatusId;
	}

	public String getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}

	public String getToStatus() {
		return toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}

	public Integer getToStatusId() {
		return toStatusId;
	}

	public void setToStatusId(Integer toStatusId) {
		this.toStatusId = toStatusId;
	}

	public List<TrasitionResponseModel> getTransitionDto() {
		return transitionDto;
	}

	public void setTransitionDto(List<TrasitionResponseModel> transitionDto) {
		this.transitionDto = transitionDto;
	}
	
	
}
