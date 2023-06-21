package com.travel.travtronics.request;

import java.util.List;

public class ShiftWorkingDaysRequest {
	
	private Integer monthNo;
	
	private Integer yearNo;
	
	private List<Integer> daysList;
	

	public Integer getMonthNo() {
		return monthNo;
	}

	public void setMonthNo(Integer monthNo) {
		this.monthNo = monthNo;
	}

	public List<Integer> getDaysList() {
		return daysList;
	}

	public void setDaysList(List<Integer> daysList) {
		this.daysList = daysList;
	}

	public Integer getYearNo() {
		return yearNo;
	}

	public void setYearNo(Integer yearNo) {
		this.yearNo = yearNo;
	}

	

}
