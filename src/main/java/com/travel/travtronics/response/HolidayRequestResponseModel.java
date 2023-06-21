package com.travel.travtronics.response;

import java.util.List;

import com.travel.travtronics.eserv.model.HolidayHeaderModel;
import com.travel.travtronics.eserv.model.HolidayLinesModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayRequestResponseModel {

	private HolidayHeaderModel holidayHeaderModel;
	private List<HolidayLinesModel> holidayLinesModel;

}
