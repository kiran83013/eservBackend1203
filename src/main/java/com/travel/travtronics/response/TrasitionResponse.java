package com.travel.travtronics.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class TrasitionResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String fromStatus;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String toStatus;

	@JsonIgnore
	private Integer fromStatusId;
	@JsonIgnore
	private Integer toStatusId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean repeatedFlag;
	private List<TrasitionResponse> transitionDto;

	public Integer getFromStatusId() {
		return fromStatusId;
	}

	public void setFromStatusId(Integer fromStatusId) {
		this.fromStatusId = fromStatusId;
	}

	public Integer getToStatusId() {
		return toStatusId;
	}

	public void setToStatusId(Integer toStatusId) {
		this.toStatusId = toStatusId;
	}

	public Boolean getRepeatedFlag() {
		return repeatedFlag;
	}

	public void setRepeatedFlag(Boolean repeatedFlag) {
		this.repeatedFlag = repeatedFlag;
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

	public List<TrasitionResponse> getTransitionDto() {
		return transitionDto;
	}

	public void setTransitionDto(List<TrasitionResponse> transitionDto) {
		this.transitionDto = transitionDto;
	}

}
