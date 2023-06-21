package com.travel.travtronics.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.ShiftsExculedMonthsModel;
import com.travel.travtronics.eserv.model.ShiftsModel;
import com.travel.travtronics.eserv.model.ShiftsWeekOffsModel;
import com.travel.travtronics.eserv.model.ShiftsWorkingDaysModel;
import com.travel.travtronics.request.ShiftWorkingDaysRequest;
import com.travel.travtronics.request.ShiftsRequest;
import com.travel.travtronics.response.ShiftsResponse;
import com.travel.travtronics.util.EnumStatus;

public class ShiftConverter {

	static LocalDateTime instance = LocalDateTime.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	static String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	public static ShiftsModel convertShiftRequestDataToModel(ShiftsRequest requestData) {

		ShiftsModel modelData = new ShiftsModel();

		modelData.setShiftName(requestData.getShiftName());
		modelData.setOrgId(requestData.getOrgId());
		modelData.setShiftCode(requestData.getShiftCode());
		modelData.setFlexiBreak(requestData.getFlexiBreak());
		modelData.setFlexiDuration(requestData.getFlexiDuration());
		modelData.setBreakTemplateId(requestData.getBreakTemplateId());
		modelData.setStartTime(requestData.getStartTime());
		modelData.setEndTime(requestData.getEndTime());
		modelData.setShiftDuration(requestData.getShiftDuration());
		modelData.setStartOfTheWeek(requestData.getStartOfTheWeek());
		modelData.setHolidayCalId(requestData.getHolidayCalId());
		modelData.setTimieZoneCode(requestData.getTimieZoneCode());
		modelData.setNotes(requestData.getNotes());
		modelData.setStartDate(requestData.getStartDate());
		modelData.setEndDate(requestData.getEndDate());
		modelData.setShiftStatus(requestData.getShiftStatus());
		modelData.setCreatedBy(requestData.getLoggedInUserId());

		return modelData;
	}


	public static List<ShiftsWeekOffsModel> convertShiftRequestDataToWeeksOffModel(Long id,
			List<Integer> weekOffsListData, Long orgId) {

		List<ShiftsWeekOffsModel> weekOffsList = new ArrayList<ShiftsWeekOffsModel>();

		for (Integer weekNo : weekOffsListData) {

			ShiftsWeekOffsModel thisLine = new ShiftsWeekOffsModel();
			
			thisLine.setOrgId(orgId);
			thisLine.setShiftId(id);
			thisLine.setWeekOffDay(weekNo);
			thisLine.setRecordStatus(EnumStatus.Active);

			weekOffsList.add(thisLine);

		}

		return weekOffsList;
	}

	public static List<ShiftsExculedMonthsModel> convertShiftRequestDataToExcludeMonthsModel(Long id,
			List<Integer> monthsListData, Long orgId) {

		List<ShiftsExculedMonthsModel> exMonthsList = new ArrayList<ShiftsExculedMonthsModel>();

		for (Integer monthNo : monthsListData) {

			ShiftsExculedMonthsModel thisLine = new ShiftsExculedMonthsModel();
	    	thisLine.setShiftId(id);
	    	thisLine.setOrgId(orgId);
			thisLine.setMonthNo(monthNo);
			thisLine.setRecordStatus(EnumStatus.Active);

			exMonthsList.add(thisLine);

		}

		return exMonthsList;
	}


	public static ShiftsModel convertUpdateShiftRequestDataToModel(ShiftsRequest requestData) {

		ShiftsModel modelData = new ShiftsModel();

		modelData.setId(requestData.getId());
		modelData.setOrgId(requestData.getOrgId());
		modelData.setShiftName(requestData.getShiftName());
		modelData.setShiftCode(requestData.getShiftCode());
		modelData.setFlexiBreak(requestData.getFlexiBreak());
		modelData.setFlexiDuration(requestData.getFlexiDuration());
		modelData.setBreakTemplateId(requestData.getBreakTemplateId());
		modelData.setStartTime(requestData.getStartTime());
		modelData.setEndTime(requestData.getEndTime());
		modelData.setShiftDuration(requestData.getShiftDuration());
		modelData.setStartOfTheWeek(requestData.getStartOfTheWeek());
		modelData.setHolidayCalId(requestData.getHolidayCalId());
		modelData.setTimieZoneCode(requestData.getTimieZoneCode());
		modelData.setNotes(requestData.getNotes());
		modelData.setStartDate(requestData.getStartDate());
		modelData.setEndDate(requestData.getEndDate());
		modelData.setShiftStatus(requestData.getShiftStatus());
		modelData.setCreatedBy(requestData.getLoggedInUserId());

		return modelData;
	}

	public static ShiftsResponse convertShiftsModelsDataToResponseData(ShiftsModel shiftData,
			List<ShiftsWeekOffsModel> weekOffsData, List<ShiftsExculedMonthsModel> excludedMonthsData,
			List<ShiftsWorkingDaysModel> workingDaysData) {

		ShiftsResponse responseData = new ShiftsResponse();
		responseData.setOrgId(shiftData.getOrgId());
		responseData.setId(shiftData.getId());
		responseData.setShiftName(shiftData.getShiftName());
		responseData.setShiftCode(shiftData.getShiftCode());
		responseData.setFlexiBreak(shiftData.getFlexiBreak());
		responseData.setFlexiDuration(shiftData.getFlexiDuration());
		responseData.setBreakTemplateId(shiftData.getBreakTemplateId());
		responseData.setStartTime(shiftData.getStartTime());
		responseData.setEndTime(shiftData.getEndTime());
		responseData.setShiftDuration(shiftData.getShiftDuration());
		responseData.setStartOfTheWeek(shiftData.getStartOfTheWeek());
		responseData.setHolidayCalId(shiftData.getHolidayCalId());
		responseData.setTimieZoneCode(shiftData.getTimieZoneCode());
		responseData.setNotes(shiftData.getNotes());
		responseData.setStartDate(shiftData.getStartDate());
		responseData.setEndDate(shiftData.getEndDate());
		responseData.setShiftStatus(shiftData.getShiftStatus());
		responseData.setCreatedBy(shiftData.getCreatedBy());
		responseData.setCreatedDate(shiftData.getCreatedDate());
		responseData.setUpdatedBy(shiftData.getUpdatedBy());
		responseData.setUpdatedDate(shiftData.getUpdatedDate());

		List<Integer> weekOffsList = new ArrayList<>();

		List<Integer> excludedMonthsList = new ArrayList<>();

		List<ShiftWorkingDaysRequest> workingDaysList = new ArrayList<>();

		for (ShiftsWeekOffsModel weekInfo : weekOffsData) {
			weekOffsList.add(weekInfo.getWeekOffDay());
		}

		for (ShiftsExculedMonthsModel monthInfo : excludedMonthsData) {
			excludedMonthsList.add(monthInfo.getMonthNo());
		}

		if (workingDaysData.size() > 0) {
			Map<Integer, Set<Integer>> worksdaysData = workingDaysData.stream()
					.collect(Collectors.groupingBy(ShiftsWorkingDaysModel::getMonthNo,
							Collectors.mapping(ShiftsWorkingDaysModel::getDayNo, Collectors.toSet())));

			for (Entry<Integer, Set<Integer>> entry : worksdaysData.entrySet()) {
				ShiftWorkingDaysRequest thisData = new ShiftWorkingDaysRequest();
				thisData.setMonthNo(entry.getKey());
				thisData.setDaysList(entry.getValue().stream().collect(Collectors.toList()));

				workingDaysList.add(thisData);
			}
		}

		responseData.setWeekOffsList(weekOffsList);
		responseData.setExcludedMonthsList(excludedMonthsList);
		responseData.setShiftWorkingDays(workingDaysList);

		return responseData;

	}

	public static List<ShiftsWorkingDaysModel> convertShiftWorkingDaysRequestDataToModelData(Long shiftId,
			List<ShiftWorkingDaysRequest> workingDaysList, Long orgId) {

		List<ShiftsWorkingDaysModel> workingDays = new ArrayList<ShiftsWorkingDaysModel>();

		if(workingDaysList.size() > 0) {
			
			for (ShiftWorkingDaysRequest wdays : workingDaysList) {
	
				Integer monthNo = wdays.getMonthNo();
				Integer yearNo = wdays.getYearNo();
	
				if (wdays.getDaysList() != null && wdays.getDaysList().isEmpty() == false &&  wdays.getDaysList().size() > 0) {
					for (Integer dayNo : wdays.getDaysList()) {
						ShiftsWorkingDaysModel wdmodel = new ShiftsWorkingDaysModel();
						
						String fullDate = "";
						if(yearNo == null || yearNo <= 0) {
							yearNo = Year.now().getValue();
							fullDate = fullDate+""+yearNo.toString();
						}else {
							fullDate = fullDate+""+yearNo.toString();
						}
						if(monthNo != null && monthNo < 10) {
							fullDate = fullDate+"-0"+monthNo.toString();
						}else {
							fullDate = fullDate+"-"+monthNo.toString();
						}
						
						if(dayNo != null && dayNo < 10) {
							fullDate = fullDate+"-0"+dayNo.toString();
						}else {
							fullDate = fullDate+"-"+dayNo.toString();
						}
						
						LocalDate localDate = LocalDate.parse(fullDate);
						
						wdmodel.setShiftId(shiftId);
						wdmodel.setYearNo(yearNo);
						wdmodel.setMonthNo(monthNo);
						wdmodel.setOrgId(orgId);
						wdmodel.setDayNo(dayNo);
						wdmodel.setWorkingFullDate(localDate);
						wdmodel.setRecordStatus(EnumStatus.Active);
	
						workingDays.add(wdmodel);
	
					}
					
				}
			}
			//System.out.println(workingDays.toString());
		}

		return workingDays;
	}



	
}
