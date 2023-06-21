package com.travel.travtronics.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.converter.BankConverter;
import com.travel.travtronics.converter.UnitShiftAssignmentConverter;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.ShiftsModel;
import com.travel.travtronics.eserv.model.SlotHeaderModel;
import com.travel.travtronics.eserv.model.UnitShiftAssignments;
import com.travel.travtronics.eserv.model.UnitShiftsSlotAssignmentModel;
import com.travel.travtronics.eserv.repository.EmployeeCustomPaginationRepository;
import com.travel.travtronics.eserv.repository.ShiftsRepository;
import com.travel.travtronics.eserv.repository.SlotHeaderRepository;
import com.travel.travtronics.eserv.repository.UnitShiftAssignmentsRepository;
import com.travel.travtronics.eserv.repository.UnitShiftsSlotAssignmentRepository;
import com.travel.travtronics.request.SearchUnitDto;
import com.travel.travtronics.request.UnitSearchDto;
import com.travel.travtronics.request.UnitShiftAssignmentRequest;
import com.travel.travtronics.request.UnitShiftSlotRelationMappedModel;
import com.travel.travtronics.request.UnitSlotShiftMappingRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.response.UnitSearchResponse;
import com.travel.travtronics.response.UnitSearchResponseDto;
import com.travel.travtronics.response.UnitShiftsSlotAssignmentResponse;
import com.travel.travtronics.response.UnitShitSlotResponse;

@Service
public class UnitShiftAssignmentsService {

	@Autowired
	UnitShiftAssignmentsRepository unitshiftassignmentsRepository;

	@Autowired
	UnitShiftsSlotAssignmentRepository unitShiftsSlotAssignmentRepository;

	@Autowired
	EmployeeCustomPaginationRepository employeeCustomPaginationRepository;

	@Transactional
	public MessageStatusResponse addShiftTemplateToUnit(List<UnitShiftAssignmentRequest> request) {
		List<UnitShiftAssignmentRequest> finalShiftAssignMentModels = new ArrayList<>();
		for (UnitShiftAssignmentRequest eachAssign : request) {
			Boolean existsByUnitIdAndShiftId = unitshiftassignmentsRepository
					.existsByUnitIdAndShiftId(eachAssign.getUnitId(), eachAssign.getShiftId());
			if (existsByUnitIdAndShiftId) {
				return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
						"invalid request received..change details and try again..!!!");
			} else {
				finalShiftAssignMentModels.add(eachAssign);
			}
		}
		unitshiftassignmentsRepository.saveAll(UnitShiftAssignmentConverter.convertJsonsToModels(request));
		return new MessageStatusResponse(HttpStatus.CREATED.value(), "successfully data saved in the system");
	}

	@Transactional
	public MessageStatusResponse modifyShiftTemplateToUnit(List<UnitShiftAssignmentRequest> request) {
		List<UnitShiftAssignmentRequest> finalShiftAssignMentModels = new ArrayList<>();
		for (UnitShiftAssignmentRequest eachAssign : request) {
			Optional<UnitShiftAssignments> assignValidation = unitshiftassignmentsRepository
					.findById(eachAssign.getId());

			if (assignValidation.isPresent()) {
				Boolean existsByUnitIdAndShiftId = unitshiftassignmentsRepository
						.existsByUnitIdAndShiftId(eachAssign.getUnitId(), eachAssign.getShiftId());
				if (existsByUnitIdAndShiftId) {
					return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
							"invalid request received..change details and try again..!!!");
				} else {
					finalShiftAssignMentModels.add(eachAssign);
				}

			} else {
				return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
						String.format("data with this id : %d is not found in our system ", eachAssign.getId()));
			}

		}
		unitshiftassignmentsRepository.saveAll(UnitShiftAssignmentConverter.convertJsonsToModels(request));
		return new MessageStatusResponse(HttpStatus.OK.value(), "successfully data saved in the system");

	}

	public APIResponse getUnitShiftsfInfoUnitById(Long id) {
		Optional<UnitShiftAssignments> assignValidation = unitshiftassignmentsRepository.findById(id);

		if (!assignValidation.isPresent()) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this id: %d is not found in our system ", id));
		} else {
			return new APIResponse(HttpStatus.OK.value(), "user shift data",
					Collections.singletonList(assignValidation.get()));
		}
	}

	public MessageStatusResponse UnitShitftSlotCreation(UnitSlotShiftMappingRequest request) {

		Boolean validation = unitShiftsSlotAssignmentRepository.existsByUnitIdAndShiftIdAndSlotTemplateId(
				request.getUnitId(), request.getShiftId(), request.getSlotTemplateId());
		if (validation) {
			return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
					"invalid request received..change details and try again..!!!");
		} else {
			unitShiftsSlotAssignmentRepository
					.save(UnitShiftAssignmentConverter.convertShiftSlotAssignJsonToModel(request));
			return new MessageStatusResponse(HttpStatus.CREATED.value(), "successfully data saved in the system");
		}
	}

	public MessageStatusResponse UnitShitftSlotUpdation(UnitSlotShiftMappingRequest request) {
		Optional<UnitShiftsSlotAssignmentModel> validateShiftSlotModel = unitShiftsSlotAssignmentRepository
				.findById(request.getId());
		if (!validateShiftSlotModel.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this id: %d is not found in our system", request.getId()));
		else {
			Boolean validation = unitShiftsSlotAssignmentRepository.existsByUnitIdAndShiftIdAndSlotTemplateId(
					request.getUnitId(), request.getShiftId(), request.getSlotTemplateId());
			if (validation) {
				return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
						"invalid request received..change details and try again..!!!");
			} else {

				unitShiftsSlotAssignmentRepository
						.save(UnitShiftAssignmentConverter.convertShiftSlotAssignJsonToModel(request));
				return new MessageStatusResponse(HttpStatus.OK.value(), "successfully data saved in the system");
			}
		}
	}

	public APIResponse getAllUnitShiftSlotfInfo(Long orgId) {
		List<Map<String, String>> responseData = unitShiftsSlotAssignmentRepository.getByOrgId(orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responseData);
	}

	public APIResponse getUnitSlotShiftsfInfoById(Long id) {
		Optional<UnitShiftsSlotAssignmentModel> validateShiftSlotModel = unitShiftsSlotAssignmentRepository
				.findById(id);
		if (!validateShiftSlotModel.isPresent())
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this id: %d is not found in our system ", id));
		else {
			List<UnitShitSlotResponse> responseData = UnitShiftAssignmentConverter
					.convertUnitShiftSlotAssignJsonsToModels(Collections.singletonList(validateShiftSlotModel.get()));
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responseData);

		}
	}

	public APIResponse getUnitSlotShiftsfInfo(Long orgId) {
		List<Map<String, Object>> shitUserMappedList = unitshiftassignmentsRepository.getShitUserMappedList(orgId);
		return new APIResponse(HttpStatus.OK.value(), "unit shift data", shitUserMappedList);
	}

	@Autowired
	UnitShiftAssignmentsRepository unitShiftAssignmentsRepository;
	@Autowired
	SlotHeaderRepository slotHeaderRepository;
	@Autowired
	ShiftsRepository shiftsRepository;

	public ResponseEntity<?> searchUnitShifts(UnitSearchDto searchDto) {
		List<UnitSearchResponse> searchUserShiftInfo = unitShiftAssignmentsRepository.searchUnitShiftInfo(searchDto);
		return new ResponseEntity<>(searchUserShiftInfo, HttpStatus.OK);
	}

	public MessageStatusResponse modifyShiftSlotAssignMents(UnitShiftSlotRelationMappedModel request) {
		MessageStatusResponse validateAssigmnetModelsRequest = validateAssigmnetRequestInfo(request);

		if (validateAssigmnetModelsRequest.getStatus() == 200) {
			unitshiftassignmentsRepository
					.save(UnitShiftAssignmentConverter.convertJsonToModels(request.getShiftAssignMentModel()));
			unitShiftsSlotAssignmentRepository.save(UnitShiftAssignmentConverter
					.convertShiftSlotAssignJsonToModels(request.getSlotShiftAssignmentModel()));
			return new MessageStatusResponse(HttpStatus.OK.value(), "data saved successfully in our system");
		} else {
			return validateAssigmnetModelsRequest;
		}

	}

	private MessageStatusResponse validateAssigmnetRequestInfo(UnitShiftSlotRelationMappedModel request) {
		Optional<UnitShiftsSlotAssignmentModel> validateShiftSlotModel = unitShiftsSlotAssignmentRepository
				.findById(request.getSlotShiftAssignmentModel().getId());
		if (!validateShiftSlotModel.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("slot-shift-assignment-info with this id: %d is not found in our system",
							request.getSlotShiftAssignmentModel()));
		Optional<UnitShiftAssignments> assignValidation = unitshiftassignmentsRepository
				.findById(request.getShiftAssignMentModel().getId());
		if (!validateShiftSlotModel.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("unit-shift-assignment-info with this id : %d is not found in our system ",
							request.getShiftAssignMentModel().getId()));
		Boolean shiftSlotValidation = unitShiftsSlotAssignmentRepository.existsByUnitIdAndShiftIdAndSlotTemplateId(
				request.getSlotShiftAssignmentModel().getUnitId(), request.getSlotShiftAssignmentModel().getShiftId(),
				request.getSlotShiftAssignmentModel().getSlotTemplateId());

		Boolean shiftUnitValidation = unitshiftassignmentsRepository.existsByUnitIdAndShiftId(
				request.getShiftAssignMentModel().getUnitId(), request.getShiftAssignMentModel().getShiftId());

		if (shiftSlotValidation || shiftUnitValidation) {
			return new MessageStatusResponse(HttpStatus.ALREADY_REPORTED.value(),
					"invalid slot-shift-assignment-request received..change details and try again..!!!");
		}

		Optional<SlotHeaderModel> slotData = slotHeaderRepository
				.findById(request.getSlotShiftAssignmentModel().getSlotTemplateId());
		if (!slotData.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("slot-template with this id: %d is not found in our system ",
							request.getSlotShiftAssignmentModel().getSlotTemplateId()));
		Optional<ShiftsModel> shiftData = shiftsRepository
				.findByShiftId(request.getShiftAssignMentModel().getShiftId());

		if (!shiftData.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("shift-template with this id: %d is not found in our system ",
							request.getShiftAssignMentModel().getShiftId()));

		/*
		 * 1.slotstartTime must be gerater than shiftStattTime --isAfter() will return
		 * true if and only if time1 is after time2 2.SlotEndTime must less than
		 * ShiftEndTime---isBefore() will return true if and only if time1 is before
		 * time2
		 */

		Boolean shiftSlotStartTimeValidation = LocalTime.parse(slotData.get().getSlotStartTime())
				.isAfter(LocalTime.parse(shiftData.get().getStartTime()))
				|| LocalTime.parse(slotData.get().getSlotStartTime())
						.compareTo(LocalTime.parse(shiftData.get().getStartTime())) == 0;

		boolean shiftSlotEndTimeValidation = LocalTime.parse(slotData.get().getSlotEndTime())
				.isBefore(LocalTime.parse(shiftData.get().getEndTime()))
				|| LocalTime.parse(slotData.get().getSlotEndTime())
						.compareTo(LocalTime.parse(shiftData.get().getEndTime())) == 0;
		if (shiftSlotStartTimeValidation == false || shiftSlotEndTimeValidation == false) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(),
					"your request cannot be processed due to in-compatable-shift-slot-timings...!!");

		}

		if (validateShiftSlotModel.isPresent() == true && assignValidation.isPresent() == true
				&& shiftSlotValidation == false && shiftUnitValidation == false && slotData.isPresent() == true
				&& shiftData.isPresent() == true && shiftSlotStartTimeValidation == true
				&& shiftSlotStartTimeValidation == true) {
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());

		}
		return new MessageStatusResponse();
	}

	public ResponseEntity<?> searchUnit(SearchUnitDto searchDto) {
		List<UnitSearchResponseDto> searchUserInfo = unitShiftAssignmentsRepository.searchUnitInfo(searchDto);

		return new ResponseEntity<>(searchUserInfo, HttpStatus.OK);
	}

	public APIResponsePaging getUnitShiftSlotPagenationByOrganization(Long orgId, Long shiftId, Long slotTemplateId,
			Long unitId, String unitName, int pageNo, int pageSize, String sortBy, SortType sortType) {

		Pageable pageable = PageRequest.of(pageNo, pageSize);

		Page<UnitShiftsSlotAssignmentModel> serviceMenuTypeData = employeeCustomPaginationRepository
				.fetchUnitShiftSlotPagination(orgId, shiftId, slotTemplateId, unitId, pageable, sortBy, sortType);

		Page<UnitShiftsSlotAssignmentResponse> pageUnitShiftSlotReponse = serviceMenuTypeData.map(model -> {
			UnitShiftsSlotAssignmentResponse response = BankConverter.convertUnitShiftSlotModelToResponse(model);

			Optional<String> orgInfo = unitShiftAssignmentsRepository.getOrgName(response.getOrgId());
			Optional<String> shift = unitShiftAssignmentsRepository.getShiftName(response.getShiftId());
			Optional<String> slot = unitShiftAssignmentsRepository.getSlotTemplateName(response.getSlotTemplateId());
			Optional<String> unit = unitShiftAssignmentsRepository.getUnitName(response.getUnitId());
			List<Map<String, String>> unitNameInfo = unitShiftAssignmentsRepository.FindByUnitNames(unitName);

			if (orgInfo.isPresent())
				response.setOrgName(orgInfo.get());

			if (shift.isPresent())
				response.setShiftName(shift.get());

			if (slot.isPresent())
				response.setSlotTemplateName(slot.get());

			if (unit.isPresent())
				response.setUnitName(unit.get());

			return response;
		});
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), pageUnitShiftSlotReponse.getContent(),
				new ArrayList<>(), pageUnitShiftSlotReponse.getNumber(), pageUnitShiftSlotReponse.getTotalElements(),
				pageUnitShiftSlotReponse.getTotalPages());
	}

}
