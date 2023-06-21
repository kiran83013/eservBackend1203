package com.travel.travtronics.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.travel.travtronics.eserv.model.AppointMentBookingModel;
import com.travel.travtronics.eserv.model.UserShiftAssignmentModel;
import com.travel.travtronics.eserv.model.UserShiftsSlotAssignmentModel;
import com.travel.travtronics.request.BookingRequest;
import com.travel.travtronics.request.ShiftAssignmentRequest;
import com.travel.travtronics.request.SlotShiftMappingRequest;
import com.travel.travtronics.response.UserShitSlotResponse;
import com.travel.travtronics.util.EnumStatus;

public class ShiftAssinmentConverter {

	public static List<UserShiftAssignmentModel> convertJsonsToModels(List<ShiftAssignmentRequest> requests) {

		return requests.stream().map(request -> {
			UserShiftAssignmentModel model = new UserShiftAssignmentModel();
			if (request.getId() != null && request.getId() != 0)
				model.setId(request.getId());
			else
				model.setId(0L);
			model.setCreatedBy(request.getCreatedBy());
			model.setStartDate(request.getStartDate());
			model.setEndDate(request.getEndDate());
			model.setOrgId(request.getOrgId());
			model.setShiftId(request.getShiftId());
			model.setUserId(request.getUserId());
			if (Objects.isNull(request.getStatus()))
				model.setStatus(EnumStatus.Active);
			else
				model.setStatus(request.getStatus());
			model.setOrgId(request.getOrgId());
			if (request.getUpdatedBy() != null && request.getUpdatedBy() != 0)
				model.setUpdatedBy(request.getUpdatedBy());
			return model;
		}).collect(Collectors.toCollection(() -> new ArrayList<>()));

	}

	public static UserShiftAssignmentModel convertJsonToModel(ShiftAssignmentRequest request) {
		UserShiftAssignmentModel model = new UserShiftAssignmentModel();
		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		else
			model.setId(0L);
		model.setCreatedBy(request.getCreatedBy());
		model.setStartDate(request.getStartDate());
		model.setOrgId(request.getOrgId());
		model.setEndDate(request.getEndDate());
		model.setShiftId(request.getShiftId());
		model.setUserId(request.getUserId());
		if (Objects.isNull(request.getStatus()))
			model.setStatus(EnumStatus.Active);
		else
			model.setStatus(request.getStatus());
		model.setOrgId(request.getOrgId());
		if (request.getUpdatedBy() != null && request.getUpdatedBy() != 0)
			model.setUpdatedBy(request.getUpdatedBy());
		return model;
	}

	public static List<ShiftAssignmentRequest> convertModelsToJsons(List<UserShiftAssignmentModel> requests) {

		return requests.stream().map(request -> {

			ShiftAssignmentRequest model = new ShiftAssignmentRequest();

			model.setId(request.getId());
			model.setCreatedBy(request.getCreatedBy());
			model.setStartDate(request.getStartDate());
			model.setEndDate(request.getEndDate());
			model.setShiftId(request.getShiftId());
			model.setUserId(request.getUserId());
			model.setStatus(request.getStatus());
			model.setOrgId(request.getOrgId());
			model.setCreatedDate(request.getCreatedDate());
			model.setUpdatedDate(request.getUpdatedDate());
			return model;
		}).collect(Collectors.toList());

	}

	public static UserShiftsSlotAssignmentModel convertShiftSlotAssignJsonToModel(SlotShiftMappingRequest request) {
		UserShiftsSlotAssignmentModel model = new UserShiftsSlotAssignmentModel();

		if (request.getId() != null && request.getId() != 0)
			model.setId(request.getId());
		else
			model.setId(0L);

		model.setCreatedBy(request.getCreatedBy());
		model.setStartDate(request.getStartDate());
		model.setOrgId(request.getOrgId());
		model.setEndDate(request.getEndDate());
		model.setShiftId(request.getShiftId());
		model.setUserId(request.getUserId());
		model.setSlotTemplateId(request.getSlotTemplateId());
		if (Objects.isNull(request.getStatus()))
			model.setStatus(EnumStatus.Active);
		else
			model.setStatus(request.getStatus());
		if (request.getUpdatedBy() != null && request.getUpdatedBy() != null)
			model.setUpdatedBy(request.getUpdatedBy());
		return model;
	}

	public static List<UserShitSlotResponse> convertShiftSlotAssignJsonsToModels(
			List<UserShiftsSlotAssignmentModel> request) {

		return request.stream().map(model -> {

			UserShitSlotResponse response = new UserShitSlotResponse();
			response.setId(model.getId());
			response.setCreatedBy(model.getCreatedBy());
			response.setStartDate(model.getStartDate());
			response.setOrgId(model.getOrgId());
			response.setEndDate(model.getEndDate());
			response.setShiftId(model.getShiftId());
			response.setUserId(model.getUserId());
			response.setSlotTemplateId(model.getSlotTemplateId());
			response.setCreatedDate(model.getCreatedDate());
			response.setUpdatedDate(model.getUpdatedDate());
			response.setUpdatedBy(model.getUpdatedBy());
			response.setStatus(model.getStatus());
			return response;

		}).collect(Collectors.toList());

	}

	public static AppointMentBookingModel convertBookingRequestToModel(BookingRequest request) {
		AppointMentBookingModel model = new AppointMentBookingModel();

		if (request.getBookingId() != null && request.getBookingId() != 0)
			model.setBookingId(request.getBookingId());
		else
			model.setBookingId(0L);
		if (request.getBookingDate() != null)
			model.setBookingDate(request.getBookingDate());
		else
			model.setBookingDate(LocalDate.now());
		if (request.getStatus() != null)
			model.setStatus(request.getStatus());
		else
			model.setStatus(EnumStatus.Active);
		model.setSlotId(request.getSlotId());
		model.setSlotLineId(request.getSlotLineId());
		model.setUserId(request.getUserId());
		model.setShiftId(request.getShiftId());
		model.setOrgId(request.getOrgId());
		model.setCreatedBy(request.getCreatedBy());
		model.setUpdatedBy(request.getUpdatedBy());
		return model;
	}

}
