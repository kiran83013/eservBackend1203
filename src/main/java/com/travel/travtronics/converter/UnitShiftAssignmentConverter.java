package com.travel.travtronics.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.UnitShiftAssignments;
import com.travel.travtronics.eserv.model.UnitShiftsSlotAssignmentModel;
import com.travel.travtronics.request.UnitShiftAssignmentRequest;
import com.travel.travtronics.request.UnitSlotShiftMappingRequest;
import com.travel.travtronics.response.UnitShitSlotResponse;
import com.travel.travtronics.util.EnumStatus;

public class UnitShiftAssignmentConverter {

	public static List<UnitShiftAssignments> convertJsonsToModels(List<UnitShiftAssignmentRequest> request) {
		
		return request.stream().map(requests -> {
			UnitShiftAssignments model = new UnitShiftAssignments();
			if (requests.getId() != null && requests.getId() != 0)
				model.setId(requests.getId());
			else
				model.setId(0L);
			model.setCreatedBy(requests.getCreatedBy());
			model.setStartDate(requests.getStartDate());
			model.setEndDate(requests.getEndDate());
			model.setOrgId(requests.getOrgId());
			model.setShiftId(requests.getShiftId());
			model.setUnitId(requests.getUnitId());
			if (Objects.isNull(requests.getStatus()))
				model.setStatus(EnumStatus.Active);
			else
				model.setStatus(requests.getStatus());
			model.setOrgId(requests.getOrgId());
			if (requests.getUpdatedBy() != null && requests.getUpdatedBy() != 0)
				model.setUpdatedBy(requests.getUpdatedBy());
			return model;
		}).collect(Collectors.toCollection(() -> new ArrayList<>()));
	}

	public static UnitShiftsSlotAssignmentModel convertShiftSlotAssignJsonToModel(UnitSlotShiftMappingRequest request) {
		UnitShiftsSlotAssignmentModel model = new UnitShiftsSlotAssignmentModel();
		
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		else
			model.setId(0L);
		
		model.setCreatedBy(request.getCreatedBy());
		
		model.setStartDate(request.getStartDate());
		model.setOrgId(request.getOrgId());
		model.setEndDate(request.getEndDate());
		model.setShiftId(request.getShiftId());
		model.setUnitId(request.getUnitId());
		model.setSlotTemplateId(request.getSlotTemplateId());
		if (Objects.isNull(request.getStatus()))
			model.setStatus(EnumStatus.Active);
		else
			model.setStatus(request.getStatus());
		if (request.getUpdatedBy() != null && request.getUpdatedBy() != null)
			model.setUpdatedBy(request.getUpdatedBy());
		return model;
		
	}

	public static List<UnitShitSlotResponse> convertUnitShiftSlotAssignJsonsToModels(
			List<UnitShiftsSlotAssignmentModel> request) {

		return request.stream().map(model -> {
			UnitShitSlotResponse response = new UnitShitSlotResponse();
			response.setId(model.getId());
			response.setCreatedBy(model.getCreatedBy());
			response.setStartDate(model.getStartDate());
			response.setOrgId(model.getOrgId());
			response.setEndDate(model.getEndDate());
			response.setShiftId(model.getShiftId());
			response.setUnitId(model.getUnitId());
			response.setSlotTemplateId(model.getSlotTemplateId());
			response.setCreatedDate(model.getCreatedDate());
			response.setUpdatedDate(model.getUpdatedDate());
			response.setUpdatedBy(model.getUpdatedBy());
			response.setStatus(model.getStatus());
			return response;
		}).collect(Collectors.toList());
	}

	public static UnitShiftAssignments convertJsonToModels(UnitShiftAssignmentRequest request) {
		UnitShiftAssignments model = new UnitShiftAssignments();
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		else
			model.setId(0L);
		model.setCreatedBy(request.getCreatedBy());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setShiftId(request.getShiftId());
		model.setUnitId(request.getUnitId());
		if (Objects.isNull(request.getStatus()))
			model.setStatus(EnumStatus.Active);
		else
			model.setStatus(request.getStatus());
		model.setOrgId(request.getOrgId());
		if (request.getUpdatedBy() != null && request.getUpdatedBy() != 0)
			model.setUpdatedBy(request.getUpdatedBy());
		return model;
		
	}

	public static UnitShiftsSlotAssignmentModel convertShiftSlotAssignJsonToModels(UnitSlotShiftMappingRequest request) {
		UnitShiftsSlotAssignmentModel model = new UnitShiftsSlotAssignmentModel();
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		else
			model.setId(0L);

		model.setCreatedBy(request.getCreatedBy());
		model.setStartDate(request.getStartDate());
		model.setEndDate(request.getEndDate());
		model.setShiftId(request.getShiftId());
		model.setUnitId(request.getUnitId());
		model.setSlotTemplateId(request.getSlotTemplateId());
		if (Objects.isNull(request.getStatus()))
			model.setStatus(EnumStatus.Active);
		else
			model.setStatus(request.getStatus());
		if (request.getUpdatedBy() != null && request.getUpdatedBy() != null)
			model.setUpdatedBy(request.getUpdatedBy());
		return model;
	}

	
}
