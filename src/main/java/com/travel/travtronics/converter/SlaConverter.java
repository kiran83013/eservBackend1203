package com.travel.travtronics.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.travel.travtronics.eserv.model.SlaHeaderModel;
import com.travel.travtronics.eserv.model.SlaLinesModel;
import com.travel.travtronics.request.SlaHeaderRequestData;
import com.travel.travtronics.request.SlaLinesRequestData;
import com.travel.travtronics.request.SlaRequestData;
import com.travel.travtronics.util.EnumStatus;

public class SlaConverter {
	
	ZonedDateTime instance = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	String currentDateTime = formatter.format(instance); // 15-02-2022 12:43

	public static SlaHeaderModel convertSlaRequestDataToHeaderModel(SlaRequestData requestData) {
		
		SlaHeaderModel model = new SlaHeaderModel();
		
		model.setOrgId(requestData.getOrgId());
		model.setBusinessUnit(requestData.getBusinessUnit());
		model.setBusiness(requestData.getBusiness());
		model.setLocation(requestData.getLocation());
		model.setCostCenter(requestData.getCostCenter());
		model.setSerivceType(requestData.getSerivceType());
		model.setShiftId(requestData.getShiftId());
		model.setSlaTime(requestData.getSlaTime());
		model.setStartDate(requestData.getStartDate());
		model.setEndDate(requestData.getEndDate());
		model.setCreatedBy(requestData.getCreatedBy());
		model.setCreatedDate(LocalDateTime.now());
		model.setRecordStatus(EnumStatus.Active);
		
		return model;
	}

	public static SlaLinesModel convertSlaLinesRequestDataToLinesModel(Long headerId, SlaLinesRequestData line, Optional<SlaLinesModel> lineSysData) {
		
		SlaLinesModel model = new SlaLinesModel();
		if(lineSysData != null && lineSysData.isPresent()) {
			model = lineSysData.get();
		}
		
		if(line.getId() != null && line.getId() > 0) {
			model.setId(line.getId());
		}		
		if (line.getOrgId() != null) {
			model.setOrgId(line.getOrgId());
		}
		if (headerId != null) {
			model.setHeaderId(headerId);
		}
		if (line.getFromStatusId() != null) {
			model.setFromStatusId(line.getFromStatusId());
		}
		if (line.getToStatusId() != null) {
			model.setToStatusId(line.getToStatusId());
		}
		if (line.getSlaName() != null) {
			model.setSlaName(line.getSlaName());
		}
		if (line.getShiftId() != null) {
			model.setShiftId(line.getShiftId());
		}
		if (line.getSlaTime() != null) {
			model.setSlaTime(line.getSlaTime());
		}
		if (line.getNotes() != null) {
			model.setNote(line.getNotes());
		}
		if (line.getStartDate() != null) {
			model.setStartDate(line.getStartDate());
		}
		if (line.getEndDate() != null) {
			model.setEndDate(line.getEndDate());
		}
		if (line.getRecordStatus() != null) {
			model.setRecordStatus(line.getRecordStatus());
		}else {
			model.setRecordStatus(EnumStatus.Active);
		}
		if(line.getId() != null && line.getId() > 0) {
			model.setUpdatedBy(line.getUpdatedBy());
		}else {
			model.setCreatedBy(line.getCreatedBy());
		}
		if(line.getId() != null && line.getId() > 0) {
			model.setUpdatedDate(LocalDateTime.now());
		}else {
			model.setCreatedDate(LocalDateTime.now());
		}
		
		return model;
	}

	public static SlaLinesRequestData convertLinesModelToResponseData(Long headerId, SlaLinesModel line) {
		
		SlaLinesRequestData response = new SlaLinesRequestData();
		
		response.setId(line.getId());
		response.setOrgId(line.getOrgId());
		response.setHeaderId(headerId);
		response.setFromStatusId(line.getFromStatusId());
		response.setToStatusId(line.getToStatusId());
		response.setSlaName(line.getSlaName());
		response.setShiftId(line.getShiftId());
		response.setSlaTime(line.getSlaTime());
		response.setNotes(line.getNote());
		response.setStartDate(line.getStartDate());
		response.setEndDate(line.getEndDate());
		response.setRecordStatus(line.getRecordStatus());
		response.setCreatedBy(line.getCreatedBy());
		response.setCreatedDate(line.getCreatedDate());
		response.setUpdatedBy(line.getUpdatedBy());
		response.setUpdatedDate(line.getUpdatedDate());
		
		return response;
	}
	
	public static SlaHeaderModel convertSlaUpdateRequestDataToHeaderModel(SlaRequestData requestData, SlaHeaderModel modelData) {

		if (requestData.getOrgId() != null) {
			modelData.setOrgId(requestData.getOrgId());
		}
		if (requestData.getBusinessUnit() != null) {
			modelData.setBusinessUnit(requestData.getBusinessUnit());
		}
		if (requestData.getBusiness() != null) {
			modelData.setBusiness(requestData.getBusiness());
		}
		if (requestData.getLocation() != null) {
			modelData.setLocation(requestData.getLocation());
		}
		if (requestData.getCostCenter() != null) {
			modelData.setCostCenter(requestData.getCostCenter());
		}
		if (requestData.getSerivceType() != null) {
			modelData.setSerivceType(requestData.getSerivceType());
		}
		if (requestData.getShiftId() != null) {
			modelData.setShiftId(requestData.getShiftId());
		}
		if (requestData.getSlaTime() != null) {
			modelData.setSlaTime(requestData.getSlaTime());
		}
		if (requestData.getStartDate() != null) {
			modelData.setStartDate(requestData.getStartDate());
		}
		if (requestData.getEndDate() != null) {
			modelData.setEndDate(requestData.getEndDate());
		}
		if (requestData.getCreatedBy() != null) {
			modelData.setUpdatedBy(requestData.getCreatedBy());
		}
		if (requestData.getRecordStatus() != null) {
			modelData.setRecordStatus(requestData.getRecordStatus());
		}

		modelData.setUpdatedDate(LocalDateTime.now());

		return modelData;
	}

	public static SlaLinesModel convertSlaLinesUpdateRequestDataToLinesModel(SlaLinesRequestData line, Optional<SlaLinesModel> dbLineInfo) {
		
		SlaLinesModel model = new SlaLinesModel();
		
		if(dbLineInfo.isPresent()) {
			model = dbLineInfo.get();
		}
		
		model.setOrgId(line.getOrgId());
		if (line.getHeaderId() != null) {
			model.setHeaderId(line.getHeaderId());
		}
		if (line.getFromStatusId() != null) {
			model.setFromStatusId(line.getFromStatusId());
		}
		if (line.getToStatusId() != null) {
			model.setToStatusId(line.getToStatusId());
		}
		if (line.getSlaName() != null) {
			model.setSlaName(line.getSlaName());
		}
		if (line.getShiftId() != null) {
			model.setShiftId(line.getShiftId());
		}
		if (line.getSlaTime() != null) {
			model.setSlaTime(line.getSlaTime());
		}
		if (line.getNotes() != null) {
			model.setNote(line.getNotes());
		}
		if (line.getStartDate() != null) {
			model.setStartDate(line.getStartDate());
		}
		if (line.getEndDate() != null) {
			model.setEndDate(line.getEndDate());
		}
		if (line.getRecordStatus() != null) {
			model.setRecordStatus(line.getRecordStatus());
		}
		if (line.getUpdatedBy() != null) {
			model.setUpdatedBy(line.getUpdatedBy());
		}
		model.setUpdatedDate(LocalDateTime.now());
		
		return model;
		
	}

	public static List<SlaLinesRequestData> convertLinesModelToResponseLinesListData(List<SlaLinesModel> linesData) {
		
		List<SlaLinesRequestData> linesList = new ArrayList<SlaLinesRequestData>();
		
		for(SlaLinesModel line : linesData) {
			
			SlaLinesRequestData response = new SlaLinesRequestData();
			
			response.setId(line.getId());
			response.setOrgId(line.getOrgId());
			response.setHeaderId(line.getHeaderId());
			response.setFromStatusId(line.getFromStatusId());
			response.setToStatusId(line.getToStatusId());
			response.setSlaName(line.getSlaName());
			response.setShiftId(line.getShiftId());
			response.setSlaTime(line.getSlaTime());
			response.setNotes(line.getNote());
			response.setStartDate(line.getStartDate());
			response.setEndDate(line.getEndDate());
			response.setRecordStatus(line.getRecordStatus());
			response.setCreatedBy(line.getCreatedBy());
			response.setCreatedDate(line.getCreatedDate());
			response.setUpdatedBy(line.getUpdatedBy());
			response.setUpdatedDate(line.getUpdatedDate());
			
			linesList.add(response);
		}
		
		return linesList;
		
	}
	
	public static SlaHeaderModel convertSlaHeaderRequestDataToHeaderModel(SlaHeaderRequestData requestData) {
		
		SlaHeaderModel model = new SlaHeaderModel();
		
		model.setOrgId(requestData.getOrgId());
		model.setBusinessUnit(requestData.getBusinessUnit());
		model.setBusiness(requestData.getBusiness());
		model.setLocation(requestData.getLocation());
		model.setCostCenter(requestData.getCostCenter());
		model.setSerivceType(requestData.getSerivceType());
		model.setShiftId(requestData.getShiftId());
		model.setSlaTime(requestData.getSlaTime());
		model.setStartDate(requestData.getStartDate());
		model.setEndDate(requestData.getEndDate());
		model.setCreatedBy(requestData.getCreatedBy());
		model.setCreatedDate(LocalDateTime.now());
		model.setRecordStatus(EnumStatus.Active);
		
		return model;
	}
	
	public static SlaHeaderModel convertSlaHeaderUpdateRequestDataToHeaderModel(Long headerId, SlaHeaderRequestData requestData) {
		
		SlaHeaderModel model = new SlaHeaderModel();
		
		model.setId(headerId);
		model.setOrgId(requestData.getOrgId());
		model.setBusinessUnit(requestData.getBusinessUnit());
		model.setBusiness(requestData.getBusiness());
		model.setLocation(requestData.getLocation());
		model.setCostCenter(requestData.getCostCenter());
		model.setSerivceType(requestData.getSerivceType());
		model.setShiftId(requestData.getShiftId());
		model.setSlaTime(requestData.getSlaTime());
		model.setStartDate(requestData.getStartDate());
		model.setEndDate(requestData.getEndDate());
		model.setCreatedBy(requestData.getCreatedBy());
		model.setCreatedDate(LocalDateTime.now());
		model.setRecordStatus(EnumStatus.Active);
		
		return model;
	}
	
}
