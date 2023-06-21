package com.travel.travtronics.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.converter.ShiftAssinmentConverter;
import com.travel.travtronics.converter.ShiftConverter;
import com.travel.travtronics.eserv.model.AppointMentBookingModel;
import com.travel.travtronics.eserv.model.BreaksHeaderModel;
import com.travel.travtronics.eserv.model.HolidayHeaderModel;
import com.travel.travtronics.eserv.model.HolidayLinesModel;
import com.travel.travtronics.eserv.model.ShiftsExculedMonthsModel;
import com.travel.travtronics.eserv.model.ShiftsModel;
import com.travel.travtronics.eserv.model.ShiftsWeekOffsModel;
import com.travel.travtronics.eserv.model.ShiftsWorkingDaysModel;
import com.travel.travtronics.eserv.model.SlotHeaderModel;
import com.travel.travtronics.eserv.model.SlotLinesModel;
import com.travel.travtronics.eserv.model.SlotShiftAssigmentEntity;
import com.travel.travtronics.eserv.model.UserShiftAssignmentModel;
import com.travel.travtronics.eserv.model.UserShiftsSlotAssignmentModel;
import com.travel.travtronics.eserv.repository.AppointMentBookingRepository;
import com.travel.travtronics.eserv.repository.BreaksHeaderRepository;
import com.travel.travtronics.eserv.repository.BreaksLinesRepository;
import com.travel.travtronics.eserv.repository.HolidayHeaderRepository;
import com.travel.travtronics.eserv.repository.HolidayLinesRepository;
import com.travel.travtronics.eserv.repository.ShiftsExculedMonthsRepository;
import com.travel.travtronics.eserv.repository.ShiftsRepository;
import com.travel.travtronics.eserv.repository.ShiftsWeekOffsRepository;
import com.travel.travtronics.eserv.repository.ShiftsWorkingDaysRepository;
import com.travel.travtronics.eserv.repository.SlotHeaderRepository;
import com.travel.travtronics.eserv.repository.SlotLinesRepository;
import com.travel.travtronics.eserv.repository.SlotShiftAssigmentEntityRepository;
import com.travel.travtronics.eserv.repository.UserShiftAssignmentRepository;
import com.travel.travtronics.eserv.repository.UserShiftsSlotAssignmentRepository;
import com.travel.travtronics.request.BookingRequest;
import com.travel.travtronics.request.SearchUserDto;
import com.travel.travtronics.request.ShiftAssignmentRequest;
import com.travel.travtronics.request.ShiftSlotRelationMappedModel;
import com.travel.travtronics.request.ShiftWorkingDaysRequest;
import com.travel.travtronics.request.ShiftsRequest;
import com.travel.travtronics.request.SlotShiftMappingRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.BreaksRequestResponseModel;
import com.travel.travtronics.response.HolidayRequestResponseModel;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.response.ShiftSlotsResponse;
import com.travel.travtronics.response.ShiftsResponse;
import com.travel.travtronics.response.SlotRequestResponseModel;
import com.travel.travtronics.response.UserSearchResponse;
import com.travel.travtronics.response.UserShiftSlotsListResponse;
import com.travel.travtronics.response.UserShiftWorkingDaysResponse;
import com.travel.travtronics.response.UserShitSlotResponse;
import com.travel.travtronics.util.EnumStatus;
import com.travel.travtronics.util.EnumYesNo;

@Service
public class ShiftService {

	@Autowired
	ShiftsRepository shiftsRepopsitory;

	@Autowired
	ShiftsWeekOffsRepository shiftsWeekOffsRepository;

	@Autowired
	ShiftsExculedMonthsRepository shiftsExculedMonthsRepository;

	@Autowired
	ShiftsWorkingDaysRepository shiftsWorkingDaysRepository;

	@Autowired
	BreaksHeaderRepository breakHeaderRepository;

	@Autowired
	BreaksLinesRepository breakLinesRepository;

	@Autowired
	SlotHeaderRepository slotHeaderRepository;

	@Autowired
	SlotLinesRepository slotLinesRepository;

	@Autowired
	HolidayHeaderRepository holidayHeaderRepository;

	@Autowired
	HolidayLinesRepository holiayLinesRepository;

	@Autowired
	UserShiftAssignmentRepository userShiftMappingRepository;

	@Autowired
	UserShiftsSlotAssignmentRepository usershiftSlotMappingRepository;

	@Autowired
	AppointMentBookingRepository bookingRepository;
	
	@Autowired
	SlotShiftAssigmentEntityRepository   slotShiftAssigmentEntityRepository;

	public MessageStatusResponse createBreakTemplate(@Valid BreaksRequestResponseModel request) {

		BreaksHeaderModel savedHeader = breakHeaderRepository.save(request.getBreaksHeaderModel());

		breakLinesRepository
				.saveAll(request.getBreaksLinesModels().stream().peek(model -> model.setHeaderId(savedHeader.getId()))
						.collect(Collectors.toCollection(() -> new ArrayList<>())));

		return new MessageStatusResponse(HttpStatus.CREATED.value(), "template created successfully");
	}

	public MessageStatusResponse modifyBreakTemplate(@Valid BreaksRequestResponseModel request) {

		Optional<BreaksHeaderModel> breakValidation = breakHeaderRepository
				.findById(request.getBreaksHeaderModel().getId());
		if (!breakValidation.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(), String.format(
					"template with this id: %d is not found in our system ", request.getBreaksHeaderModel().getId()));
		BreaksHeaderModel savedHeader = breakHeaderRepository.save(request.getBreaksHeaderModel());

		breakLinesRepository
				.saveAll(request.getBreaksLinesModels().stream().peek(model -> model.setHeaderId(savedHeader.getId()))
						.collect(Collectors.toCollection(() -> new ArrayList<>())));

		return new MessageStatusResponse(HttpStatus.OK.value(), "template modified successfully");

	}

	public APIResponse getBreakTemplateInfo(Long id) {
		Optional<BreaksHeaderModel> breakValidation = breakHeaderRepository.findById(id);
		if (!breakValidation.isPresent())
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("template with this id: %d is not found in our system ", id));
		else {
			BreaksRequestResponseModel responseModel = new BreaksRequestResponseModel();
			responseModel.setBreaksHeaderModel(breakValidation.get());
			responseModel.setBreaksLinesModels(
					breakLinesRepository.findAllByHeaderIdAndIsDeletedIsNull(breakValidation.get().getId()));
			return new APIResponse(HttpStatus.OK.value(), "template info", Collections.singletonList(responseModel));
		}

	}

	public APIResponse getBreakTemplatesInfo(Long orgId) {
		return new APIResponse(HttpStatus.OK.value(), "template info", breakHeaderRepository.findByOrgId(orgId));
	}

	public MessageStatusResponse createSlotTemplate(@Valid SlotRequestResponseModel request) {
		List<SlotLinesModel> collectedSlotLines = request.getSlotLineModels().stream().map(eachSlot -> {

			Boolean afterSlotValidation = LocalTime.parse(eachSlot.getStartTime())
					.isAfter(LocalTime.parse(request.getSlotHeaderModel().getSlotStartTime()))
					|| LocalTime.parse(eachSlot.getStartTime())
							.compareTo(LocalTime.parse(request.getSlotHeaderModel().getSlotStartTime())) == 0;

			boolean beforeSlotValidation = LocalTime.parse(eachSlot.getEndTime())
					.isBefore(LocalTime.parse(request.getSlotHeaderModel().getSlotEndTime()))
					|| LocalTime.parse(eachSlot.getEndTime())
							.compareTo(LocalTime.parse(request.getSlotHeaderModel().getSlotEndTime())) == 0;
			if (afterSlotValidation == true && beforeSlotValidation == true)
				return eachSlot;
			else
				throw new RuntimeException("your request cannot be processed due to in-compatable-slot-timings...!!");
		}).collect(Collectors.toCollection(() -> new ArrayList<>()));

		SlotHeaderModel savedSlot = slotHeaderRepository.save(request.getSlotHeaderModel());
		slotLinesRepository.saveAll(collectedSlotLines.stream().peek(model -> model.setHeaderId(savedSlot.getId()))
				.collect(Collectors.toList()));
		return new MessageStatusResponse(HttpStatus.CREATED.value(), "template created successfully");
	}

	public MessageStatusResponse modifySlotTemplate(@Valid SlotRequestResponseModel request) {
		Optional<SlotHeaderModel> slotValidation = slotHeaderRepository.findById(request.getSlotHeaderModel().getId());
		if (!slotValidation.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(), String.format(
					"template with this id: %d is not found in our system ", request.getSlotHeaderModel().getId()));
		else {
			List<SlotLinesModel> collectedSlotLines = request.getSlotLineModels().stream().map(eachSlot -> {

				Boolean afterSlotValidation = LocalTime.parse(eachSlot.getStartTime())
						.isAfter(LocalTime.parse(request.getSlotHeaderModel().getSlotStartTime()))
						|| LocalTime.parse(eachSlot.getStartTime())
								.compareTo(LocalTime.parse(request.getSlotHeaderModel().getSlotStartTime())) == 0;

				boolean beforeSlotValidation = LocalTime.parse(eachSlot.getEndTime())
						.isBefore(LocalTime.parse(request.getSlotHeaderModel().getSlotEndTime()))
						|| LocalTime.parse(eachSlot.getEndTime())
								.compareTo(LocalTime.parse(request.getSlotHeaderModel().getSlotEndTime())) == 0;
				if (afterSlotValidation == true && beforeSlotValidation == true)
					return eachSlot;
				else
					throw new RuntimeException(
							"your request cannot be processed due to in-compatable-slot-timings...!!");
			}).collect(Collectors.toCollection(() -> new ArrayList<>()));

			SlotHeaderModel savedSlot = slotHeaderRepository.save(request.getSlotHeaderModel());
			slotLinesRepository.saveAll(collectedSlotLines.stream().peek(model -> model.setHeaderId(savedSlot.getId()))
					.collect(Collectors.toList()));
			return new MessageStatusResponse(HttpStatus.OK.value(), "template modified successfully");
		}
	}

	public APIResponse getSlotTemplateInfo(Long id) {
		Optional<SlotHeaderModel> slotValidation = slotHeaderRepository.findById(id);
		if (!slotValidation.isPresent())
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("template with this id: %d is not found in our system ", id));
		else {
			SlotRequestResponseModel responseModel = new SlotRequestResponseModel();
			responseModel.setSlotHeaderModel(slotValidation.get());
			responseModel.setSlotLineModels(
					slotLinesRepository.findAllByHeaderIdAndIsDeletedIsNull(slotValidation.get().getId()));
			return new APIResponse(HttpStatus.OK.value(), "template info", Collections.singletonList(responseModel));
		}
	}

	public APIResponse getSlotTemplatesInfo(Long orgId) {
		return new APIResponse(HttpStatus.OK.value(), "template info", slotHeaderRepository.findByOrgId(orgId));
	}

	public MessageStatusResponse createHolidayTemplate(@Valid HolidayRequestResponseModel request) {

		HolidayHeaderModel savedHoliday = holidayHeaderRepository.save(request.getHolidayHeaderModel());

		holiayLinesRepository
				.saveAll(request.getHolidayLinesModel().stream().peek(model -> model.setHeaderId(savedHoliday.getId()))
						.collect(Collectors.toCollection(() -> new ArrayList<>())));

		return new MessageStatusResponse(HttpStatus.CREATED.value(), "template created successfully");
	}

	public MessageStatusResponse modifyHolidayTemplate(@Valid HolidayRequestResponseModel request) {
		Optional<HolidayHeaderModel> holidayValidation = holidayHeaderRepository
				.findById(request.getHolidayHeaderModel().getId());
		if (!holidayValidation.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(), String.format(
					"template with this id: %d is not found in our system ", request.getHolidayHeaderModel().getId()));
		HolidayHeaderModel savedHoliday = holidayHeaderRepository.save(request.getHolidayHeaderModel());

		holiayLinesRepository
				.saveAll(request.getHolidayLinesModel().stream().peek(model -> model.setHeaderId(savedHoliday.getId()))
						.collect(Collectors.toCollection(() -> new ArrayList<>())));

		return new MessageStatusResponse(HttpStatus.OK.value(), "template modified successfully");
	}

	public APIResponse getHolidayTemplateInfo(Long id) {
		Optional<HolidayHeaderModel> holidayValidation = holidayHeaderRepository.findById(id);
		if (!holidayValidation.isPresent())
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("template with this id: %d is not found in our system ", id));
		else {
			HolidayRequestResponseModel responseModel = new HolidayRequestResponseModel();
			responseModel.setHolidayHeaderModel(holidayValidation.get());
			responseModel.setHolidayLinesModel(
					holiayLinesRepository.findAllByHeaderIdAndIsDeletedIsNull(holidayValidation.get().getId()));
			return new APIResponse(HttpStatus.OK.value(), "template info", Collections.singletonList(responseModel));
		}

	}

	public APIResponse getHolidayTemplatesInfo(Long orgId) {
		return new APIResponse(HttpStatus.OK.value(), "template info", holidayHeaderRepository.findByOrgId(orgId));
	}

	public MessageStatusResponse createShiftsTemplateData(@Valid ShiftsRequest request) {

		if (request.getShiftName().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "shiftName should not be empty");
		}

		if (request.getShiftCode().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "shiftCode should not be empty");
		}

		if (request.getStartTime().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "startTime should not be empty");
		}

		if (request.getEndTime().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "endTime should not be empty");
		}

		if (Double.valueOf(request.getShiftDuration()) <= 0.0) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(),
					"shiftDuration should be grater than zero");
		}

		ShiftsModel savedShiftData = shiftsRepopsitory.save(ShiftConverter.convertShiftRequestDataToModel(request));

		if (savedShiftData.getId() > 0) {

			Long shiftId = savedShiftData.getId();
            Long orgId = savedShiftData.getOrgId();
			if (request.getWeekOffsList().size() > 0) {
				List<ShiftsWeekOffsModel> weeksOffsData = shiftsWeekOffsRepository.saveAll(
						ShiftConverter.convertShiftRequestDataToWeeksOffModel(shiftId, request.getWeekOffsList(),orgId));
			}

			if (request.getExcludedMonthsList().size() > 0) {
				List<ShiftsExculedMonthsModel> exMonthsData = shiftsExculedMonthsRepository.saveAll(ShiftConverter
						.convertShiftRequestDataToExcludeMonthsModel(shiftId, request.getExcludedMonthsList(),orgId));
			}

			if (request.getShiftWorkingDays().size() > 0) {
				List<ShiftsWorkingDaysModel> workingDaysList = convertShiftWorkingDaysRequestDataToModelData(shiftId, request.getShiftWorkingDays(),orgId, savedShiftData);
				if (workingDaysList.size() > 0) {
					List<ShiftsWorkingDaysModel> savedWorkingdaysData = shiftsWorkingDaysRepository
							.saveAll(workingDaysList);
				}
			}

			return new MessageStatusResponse(HttpStatus.OK.value(), "Successfully data saved in the system");
		} else {
			return new MessageStatusResponse(HttpStatus.FAILED_DEPENDENCY.value(),
					"Failed, Data not saved in the system");
		}
	}

	public MessageStatusResponse updateShiftsTemplateData(@Valid ShiftsRequest request) {

		if (request.getShiftName().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "shiftName should not be empty");
		}

		if (request.getShiftCode().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "shiftCode should not be empty");
		}

		if (request.getStartTime().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "startTime should not be empty");
		}

		if (request.getEndTime().isBlank()) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "endTime should not be empty");
		}

		if (Double.valueOf(request.getShiftDuration()) <= 0) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(),
					"shiftDuration should be grater than zero");
		}

		if (request.getId() == null || request.getId() <= 0) {
			return new MessageStatusResponse(HttpStatus.BAD_REQUEST.value(), "shiftId should not be empty or zero");
		} else {

			Optional<ShiftsModel> shiftData = shiftsRepopsitory.findById(request.getId());
			if (shiftData.isPresent() == false) {
				return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
						"This Shift data not found with provided id in the system.");
			}

		}

		ShiftsModel saveData = shiftsRepopsitory.save(ShiftConverter.convertUpdateShiftRequestDataToModel(request));

		if (saveData.getId() > 0) {

			Long shiftId = saveData.getId();

			if (request.getWeekOffsList().size() > 0) {

				shiftsWeekOffsRepository.deleteWeekOffsByShiftId(shiftId);

				List<ShiftsWeekOffsModel> weeksOffsData = shiftsWeekOffsRepository.saveAll(
						ShiftConverter.convertShiftRequestDataToWeeksOffModel(shiftId, request.getWeekOffsList(),request.getOrgId()));
			}

			if (request.getExcludedMonthsList().size() > 0) {

				shiftsExculedMonthsRepository.deleteExMonthsByShiftId(shiftId);

				List<ShiftsExculedMonthsModel> exMonthsData = shiftsExculedMonthsRepository.saveAll(ShiftConverter
						.convertShiftRequestDataToExcludeMonthsModel(shiftId, request.getExcludedMonthsList(),request.getOrgId()));
			}

			if (request.getShiftWorkingDays().size() > 0) {

				shiftsWorkingDaysRepository.deleteWorkingDaysByShiftId(shiftId);

				List<ShiftsWorkingDaysModel> workingDaysList = convertShiftWorkingDaysRequestDataToModelData(shiftId, request.getShiftWorkingDays(),request.getOrgId(), saveData);
				// System.out.println(workingDaysList.size());
				if (workingDaysList.size() > 0) {
					List<ShiftsWorkingDaysModel> savedWorkingdaysData = shiftsWorkingDaysRepository
							.saveAll(workingDaysList);
					// System.out.println(savedWorkingdaysData.toString());
				}
			}

			return new MessageStatusResponse(HttpStatus.OK.value(), "Successfully data saved in the system");
		} else {
			return new MessageStatusResponse(HttpStatus.FAILED_DEPENDENCY.value(),
					"Failed, Data not saved in the system");
		}

	}

	public APIResponse getShiftsfInfoDataById(Long shiftId) {

		if (shiftId == null || shiftId <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "shiftId should not be empty or zero",
					Collections.emptyList());
		}

		Optional<ShiftsModel> shiftData = shiftsRepopsitory.findById(shiftId);

		if (shiftData.isPresent()) {

			List<ShiftsWeekOffsModel> weekOffsData = shiftsWeekOffsRepository.findByShiftId(shiftId);

			List<ShiftsExculedMonthsModel> excludedMonthsData = shiftsExculedMonthsRepository.findByShiftId(shiftId);

			List<ShiftsWorkingDaysModel> workingDaysData = shiftsWorkingDaysRepository
					.getAllWorkingDaysByShiftId(shiftId);

			ShiftsResponse responseData = ShiftConverter.convertShiftsModelsDataToResponseData(shiftData.get(),
					weekOffsData, excludedMonthsData, workingDaysData);

			return new APIResponse(HttpStatus.OK.value(), "Shift data", Collections.singletonList(responseData));

		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Data not found with provided id in the system.",
					Collections.emptyList());
		}
	}

	public APIResponse getAllShiftsfInfoData(Long orgId) {

		List<ShiftsModel> shiftListData = shiftsRepopsitory.findByOrgId(orgId);

		List<ShiftsResponse> responseData = new ArrayList<ShiftsResponse>();

		if (shiftListData.size() > 0) {

			for (ShiftsModel data : shiftListData) {

				List<ShiftsWeekOffsModel> weekOffsData = shiftsWeekOffsRepository.findByShiftId(data.getId());

				List<ShiftsExculedMonthsModel> excludedMonthsData = shiftsExculedMonthsRepository
						.findByShiftId(data.getId());

				List<ShiftsWorkingDaysModel> workingDaysData = shiftsWorkingDaysRepository
						.getAllWorkingDaysByShiftId(data.getId());

				ShiftsResponse thisData = ShiftConverter.convertShiftsModelsDataToResponseData(data, weekOffsData,
						excludedMonthsData, workingDaysData);

				responseData.add(thisData);

			}

			return new APIResponse(HttpStatus.OK.value(), "Shift data", responseData);

		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "Data not found with provided id in the system.",
					Collections.emptyList());
		}

	}

	@Transactional
	public MessageStatusResponse addShiftTemplateToUser(@Valid List<ShiftAssignmentRequest> request) {

		List<ShiftAssignmentRequest> finalShiftAssignMentModels = new ArrayList<>();

		for (ShiftAssignmentRequest eachAssign : request) {
			Boolean existsByUserIdAndShiftId = userShiftMappingRepository
					.existsByUserIdAndShiftId(eachAssign.getUserId(), eachAssign.getShiftId());

			if (existsByUserIdAndShiftId) {
				return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
						"invalid request received..change details and try again..!!!");
			} else {
				finalShiftAssignMentModels.add(eachAssign);
			}
		}

		userShiftMappingRepository.saveAll(ShiftAssinmentConverter.convertJsonsToModels(request));
		return new MessageStatusResponse(HttpStatus.CREATED.value(), "successfully data saved in the system");
	}

	public MessageStatusResponse UserShitftSlotCreation(@Valid SlotShiftMappingRequest request) {

		Boolean validation = usershiftSlotMappingRepository.existsByUserIdAndShiftIdAndSlotTemplateId(
				request.getUserId(), request.getShiftId(), request.getSlotTemplateId());

		if (validation) {
			return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
					"invalid request received..change details and try again..!!!");
		} else {
			usershiftSlotMappingRepository.save(ShiftAssinmentConverter.convertShiftSlotAssignJsonToModel(request));
			return new MessageStatusResponse(HttpStatus.CREATED.value(), "successfully data saved in the system");
		}

	}

	public MessageStatusResponse UserShitftSlotUpdation(@Valid SlotShiftMappingRequest request) {

		Optional<UserShiftsSlotAssignmentModel> validateShiftSlotModel = usershiftSlotMappingRepository
				.findById(request.getId());
		if (!validateShiftSlotModel.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this id: %d is not found in our system", request.getId()));
		else {

			Boolean validation = usershiftSlotMappingRepository.existsByUserIdAndShiftIdAndSlotTemplateId(
					request.getUserId(), request.getShiftId(), request.getSlotTemplateId());

			if (validation) {
				return new MessageStatusResponse(HttpStatus.CONFLICT.value(),
						"invalid request received..change details and try again..!!!");
			} else {

				usershiftSlotMappingRepository.save(ShiftAssinmentConverter.convertShiftSlotAssignJsonToModel(request));
				return new MessageStatusResponse(HttpStatus.OK.value(), "successfully data saved in the system");
			}
		}

	}

	public APIResponse getAllUserShiftSlotfInfo(Long orgId) {
	List<SlotShiftAssigmentEntity> responseData = slotShiftAssigmentEntityRepository.findByOrgId(orgId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responseData);
	}

	public APIResponse getUserSlotShiftsfInfoById(Long id) {
		Optional<UserShiftsSlotAssignmentModel> validateShiftSlotModel = usershiftSlotMappingRepository.findById(id);
		if (!validateShiftSlotModel.isPresent())
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this id: %d is not found in our system ", id));
		else {
			List<UserShitSlotResponse> responseData = ShiftAssinmentConverter
					.convertShiftSlotAssignJsonsToModels(Collections.singletonList(validateShiftSlotModel.get()));
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), responseData);
		}

	}

	@Transactional
	public MessageStatusResponse modifyShiftTemplateToUser(@Valid List<ShiftAssignmentRequest> request) {

		List<ShiftAssignmentRequest> finalShiftAssignMentModels = new ArrayList<>();

		for (ShiftAssignmentRequest eachAssign : request) {

			Optional<UserShiftAssignmentModel> assignValidation = userShiftMappingRepository
					.findById(eachAssign.getId());

			if (assignValidation.isPresent()) {

				Boolean existsByUserIdAndShiftId = userShiftMappingRepository
						.existsByUserIdAndShiftId(eachAssign.getUserId(), eachAssign.getShiftId());

				if (existsByUserIdAndShiftId) {
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
		userShiftMappingRepository.saveAll(ShiftAssinmentConverter.convertJsonsToModels(request));
		return new MessageStatusResponse(HttpStatus.OK.value(), "successfully data saved in the system");

	}

	public APIResponse getUserShiftsfInfoUserById(Long id) {
		Optional<UserShiftAssignmentModel> assignValidation = userShiftMappingRepository.findById(id);

		if (!assignValidation.isPresent()) {
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this id: %d is not found in our system ", id));
		} else {
			return new APIResponse(HttpStatus.OK.value(), "user shift data",
					Collections.singletonList(assignValidation.get()));
		}

	}

	public APIResponse getUserSlotShiftsfInfo(Long orgId) {
		List<Map<String, Object>> shitUserMappedList = userShiftMappingRepository.getShitUserMappedList(orgId);
		return new APIResponse(HttpStatus.OK.value(), "user shift data", shitUserMappedList);
	}

	public APIResponse getSlotTemplateInfoByShiftId(Long shiftId) {
		List<SlotHeaderModel> slotInfoByShiftId = slotHeaderRepository.getSlotInfoByShiftId(shiftId);

		if (slotInfoByShiftId.isEmpty())
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("data with this shiftId: %d is not found in our system ", shiftId));
		else {

			List<SlotRequestResponseModel> collectedSlotInfo = slotInfoByShiftId.stream().map(eachSlot -> {
				SlotRequestResponseModel responseModel = new SlotRequestResponseModel();
				responseModel.setSlotHeaderModel(eachSlot);
				responseModel
						.setSlotLineModels(slotLinesRepository.findAllByHeaderIdAndIsDeletedIsNull(eachSlot.getId()));
				return responseModel;
			}).collect(Collectors.toCollection(ArrayList::new));

			return new APIResponse(HttpStatus.OK.value(), "slot data", collectedSlotInfo);

		}

	}

	public APIResponse getUserShiftWorkingDaysInfo(Long userId, Integer monthNo, Integer year) {

		if (userId == null || userId <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "userId should not be empty or zero",
					Collections.emptyList());
		}
		if (monthNo == null || monthNo <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "monthNo should not be empty or zero",
					Collections.emptyList());
		}
		if (year == null || year <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "year should not be empty or zero",
					Collections.emptyList());
		}

		List<UserShiftAssignmentModel> userShiftList = userShiftMappingRepository
				.getUserShiftAssignmentsByUserId(userId);

		List<UserShiftWorkingDaysResponse> responseData = new ArrayList<>();

		if (userShiftList.size() > 0) {

			java.util.Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int currMonthNo = cal.get(Calendar.MONTH);
			int currYear = cal.get(Calendar.YEAR);

			String inputDate = year + "-" + monthNo + "-01";

			for (UserShiftAssignmentModel userShift : userShiftList) {

				Long shiftId = userShift.getShiftId();

				Optional<ShiftsModel> shiftData = shiftsRepopsitory.getShiftInfoByIdAndDate(shiftId, inputDate);

				if (shiftData.isPresent()) {

					Optional<ShiftsExculedMonthsModel> excldMonths = shiftsExculedMonthsRepository
							.checkExcludedMonthByShiftIdMonthNo(shiftId, monthNo);

					if (excldMonths.isPresent() == false) {

						YearMonth yearMonthObject = YearMonth.of(year, monthNo);
						int daysInMonth = yearMonthObject.lengthOfMonth();

						for (int d = 1; d <= daysInMonth; d++) {

							String thisDate = year + "-" + monthNo + "-" + d;

							Calendar c = Calendar.getInstance();
							c.set(year, monthNo, d);
							int dayOfWeekNo = c.get(Calendar.DAY_OF_WEEK);

							Optional<ShiftsWeekOffsModel> weekOff = shiftsWeekOffsRepository
									.isThisDayWeekOffByShiftIdDayNo(shiftId, dayOfWeekNo);

							Optional<ShiftsWorkingDaysModel> workDay = shiftsWorkingDaysRepository
									.isThisDayWorkingDayByShiftIdMonthNoDayNo(shiftId, monthNo, d);

							UserShiftWorkingDaysResponse thisInfo = new UserShiftWorkingDaysResponse();
							thisInfo.setUserId(userId);
							thisInfo.setShiftId(userShift.getShiftId());
							thisInfo.setMonthNo(monthNo);
							thisInfo.setYearNo(year);
							thisInfo.setDayDate(thisDate);

							if (weekOff.isPresent()) {
								thisInfo.setWeekOff(1);
							} else {
								thisInfo.setWeekOff(0);
							}

							if (workDay.isPresent()) {
								thisInfo.setIsItHoliday(0);
							} else {
								thisInfo.setIsItHoliday(1);
							}

							responseData.add(thisInfo);
						}
					}
				}

			}
			if (responseData.size() > 0) {
				return new APIResponse(HttpStatus.OK.value(), "Working days data",
						Collections.singletonList(responseData));
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
						Collections.emptyList());
			}

		} else {

			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
					Collections.emptyList());

		}

	}

	public APIResponse getUserShiftSlotsList(Long userId, Long shiftId, Date inputDate) {

		if (userId == null || userId <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "userId should not be empty or zero",
					Collections.emptyList());
		}
		if (shiftId == null || shiftId <= 0) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "shiftId should not be empty or zero",
					Collections.emptyList());
		}
		if (inputDate == null) {
			return new APIResponse(HttpStatus.BAD_REQUEST.value(), "inputDate should not be empty",
					Collections.emptyList());
		}

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		String inputDateStr = dt1.format(inputDate);

		List<UserShiftSlotsListResponse> responseData = new ArrayList<>();

		List<UserShiftsSlotAssignmentModel> userShiftSlots = usershiftSlotMappingRepository
				.getUserShiftsByUserIdShiftId(userId, shiftId, inputDateStr);

		if (userShiftSlots.size() > 0) {

			for (UserShiftsSlotAssignmentModel slots : userShiftSlots) {
				Optional<SlotHeaderModel> headerInfo = slotHeaderRepository
						.getSlotHeaderInfoById(slots.getSlotTemplateId());
				if (headerInfo.isPresent()) {
					UserShiftSlotsListResponse thisData = new UserShiftSlotsListResponse();
					thisData.setShiftId(shiftId);
					thisData.setUserId(userId);

					List<SlotLinesModel> slotsList = slotLinesRepository
							.getSlotsListInfoByHeaderId(headerInfo.get().getId());
					if (slotsList.size() > 0) {

						List<ShiftSlotsResponse> slotsDataList = new ArrayList<>();
						for (SlotLinesModel slot : slotsList) {
							ShiftSlotsResponse thisSlot = new ShiftSlotsResponse();
							thisSlot.setHeaderId(slot.getHeaderId());
							thisSlot.setSlotName(slot.getName());
							thisSlot.setOrgId(slot.getOrgId());
							thisSlot.setStartTime(slot.getStartTime());
							thisSlot.setEndTime(slot.getEndTime());
							thisSlot.setDuration(slot.getDuration());
							/*
							 * added to verify whether this slot is booked or not
							 */
							thisSlot.setLineId(slot.getId());
							thisSlot.setIsSlotBooked(bookingRepository.generateBookingValidation(shiftId,
									slot.getHeaderId(), slot.getId(), userId));
							slotsDataList.add(thisSlot);
						}

						thisData.setSlotsList(slotsDataList);
					}
					responseData.add(thisData);
				}
			}

			if (responseData.size() > 0) {
				return new APIResponse(HttpStatus.OK.value(), "Shift slots data",
						Collections.singletonList(responseData));
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
						Collections.emptyList());
			}

		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), "No data found in the system",
					Collections.emptyList());
		}
	}

	public MessageStatusResponse modifyShiftSlotAssignMents(@Valid ShiftSlotRelationMappedModel request) {
		MessageStatusResponse validateAssigmnetModelsRequest = validateAssigmnetRequestInfo(request);

		if (validateAssigmnetModelsRequest.getStatus() == 200) {

			userShiftMappingRepository
					.save(ShiftAssinmentConverter.convertJsonToModel(request.getShiftAssignMentModel()));

			usershiftSlotMappingRepository.save(
					ShiftAssinmentConverter.convertShiftSlotAssignJsonToModel(request.getSlotShiftAssignmentModel()));
			return new MessageStatusResponse(HttpStatus.OK.value(), "data saved successfully in our system");

		} else {
//			return new MessageStatusResponse(HttpStatus.EXPECTATION_FAILED.value(),
//					"unable to save data  in our system");

			return validateAssigmnetModelsRequest;
		}

	}

	public MessageStatusResponse validateAssigmnetRequestInfo(ShiftSlotRelationMappedModel request) {

		Optional<UserShiftsSlotAssignmentModel> validateShiftSlotModel = usershiftSlotMappingRepository
				.findById(request.getSlotShiftAssignmentModel().getId());

		if (!validateShiftSlotModel.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("slot-shift-assignment-info with this id: %d is not found in our system",
							request.getSlotShiftAssignmentModel().getId()));

		Optional<UserShiftAssignmentModel> assignValidation = userShiftMappingRepository
				.findById(request.getShiftAssignMentModel().getId());

		if (!validateShiftSlotModel.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("user-shift-assignment-info with this id : %d is not found in our system ",
							request.getShiftAssignMentModel().getId()));

		Boolean shiftSlotValidation = usershiftSlotMappingRepository.existsByUserIdAndShiftIdAndSlotTemplateId(
				request.getSlotShiftAssignmentModel().getUserId(), request.getSlotShiftAssignmentModel().getShiftId(),
				request.getSlotShiftAssignmentModel().getSlotTemplateId());

		Boolean shiftUserValidation = userShiftMappingRepository.existsByUserIdAndShiftId(
				request.getShiftAssignMentModel().getUserId(), request.getShiftAssignMentModel().getShiftId());

		if (shiftSlotValidation || shiftUserValidation) {
			return new MessageStatusResponse(HttpStatus.ALREADY_REPORTED.value(),
					"invalid slot-shift-assignment-request received..change details and try again..!!!");
		}

		Optional<SlotHeaderModel> slotData = slotHeaderRepository
				.findById(request.getSlotShiftAssignmentModel().getSlotTemplateId());
		if (!slotData.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("slot-template with this id: %d is not found in our system ",
							request.getSlotShiftAssignmentModel().getSlotTemplateId()));

		Optional<ShiftsModel> shiftData = shiftsRepopsitory
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
				&& shiftSlotValidation == false && shiftUserValidation == false && slotData.isPresent() == true
				&& shiftData.isPresent() == true && shiftSlotStartTimeValidation == true
				&& shiftSlotStartTimeValidation == true) {
			return new MessageStatusResponse(HttpStatus.OK.value(), HttpStatus.OK.name());

		}
		return new MessageStatusResponse();

	}

	public MessageStatusResponse bookAnAppointMent(@Valid BookingRequest request) {

		Boolean bookingValidation = bookingRepository.generateBookingValidation(request.getShiftId(),
				request.getSlotId(), request.getSlotLineId(), request.getUserId());

		if (bookingValidation) {
			return new MessageStatusResponse(HttpStatus.ALREADY_REPORTED.value(),
					"your're appointment has not been processed ..due to this slot alreday booked previously");
		}

		bookingRepository.save(ShiftAssinmentConverter.convertBookingRequestToModel(request));
		return new MessageStatusResponse(HttpStatus.OK.value(), "congrats,your appointment has been booked");
	}

	public MessageStatusResponse modifyAnAppointMent(@Valid BookingRequest request) {

		Optional<AppointMentBookingModel> bookingValidation = bookingRepository.findById(request.getBookingId());

		if (!bookingValidation.isPresent())
			return new MessageStatusResponse(HttpStatus.NOT_FOUND.value(),
					String.format("appointment with this id: %d is not found in our system ", request.getBookingId()));
		else {

			Boolean bookingDuplicateValidation = bookingRepository.generateBookingValidation(request.getShiftId(),
					request.getSlotId(), request.getSlotLineId(), request.getUserId());

			if (bookingDuplicateValidation) {
				return new MessageStatusResponse(HttpStatus.ALREADY_REPORTED.value(),
						"your're appointment has not been processed ..due to this slot alreday booked previously");
			}
			bookingRepository.save(ShiftAssinmentConverter.convertBookingRequestToModel(request));
			return new MessageStatusResponse(HttpStatus.OK.value(), "your booked appointment has been modified");
		}

	}

	public APIResponse getAppointmentById(Long bookingId) {
		Optional<AppointMentBookingModel> bookingValidation = bookingRepository.findById(bookingId);

		if (!bookingValidation.isPresent())
			return new APIResponse(HttpStatus.NOT_FOUND.value(),
					String.format("appointment with this id: %d is not found in our system ", bookingId));
		else
			return new APIResponse(HttpStatus.OK.value(), "appointmnet-info",
					Collections.singletonList(bookingValidation.get()));
	}

	public APIResponse getAppointmentInfo(Long orgId) {
		return new APIResponse(HttpStatus.OK.value(), "appointmnet-info", bookingRepository.getBookedAppointMentList(orgId));
	}
	
	
	public ResponseEntity<?> searchUserShifts(SearchUserDto searchDto) {
		List<UserSearchResponse> searchUserShiftInfo = bookingRepository.searchUserShiftInfo(searchDto);
		return new ResponseEntity<>(searchUserShiftInfo, HttpStatus.OK);
	}
	
	public List<ShiftsWorkingDaysModel> convertShiftWorkingDaysRequestDataToModelData(Long shiftId,
			List<ShiftWorkingDaysRequest> workingDaysList, Long orgId, ShiftsModel shiftData) {

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
						
						int durationMinutes = 0;
						String numberValueStr = shiftData.getShiftDuration();
						String[] duarionParts = numberValueStr.split("\\.");				        
				        if(duarionParts.length > 0){
				        	durationMinutes = Integer.valueOf(duarionParts[0]) * 60;
				        	if(duarionParts.length > 1){
				        		durationMinutes = durationMinutes + Integer.valueOf(duarionParts[1]);
				        	}
				        }				        
						System.out.println(numberValueStr+": duration: "+durationMinutes);					
						
						String startTime = shiftData.getStartTime().toString();
				        String thisDateStartTime = fullDate+"T"+startTime+":00";
				        
				        //LocalTime time = LocalTime.parse(shiftDurationValue.toString(), DateTimeFormatter.ofPattern("H.mm"));
				        //int durationMinutes = time.getHour() * 60 + time.getMinute();

				        LocalDateTime dateTime = LocalDateTime.parse(thisDateStartTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
				        LocalDateTime newDateTime = dateTime.plusMinutes(durationMinutes);
				        String endTime = newDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
				        if (newDateTime.getDayOfYear() > dateTime.getDayOfYear()) {
				            endTime = "23:59:00";
				        }
				        
				        LocalTime endTimeFormate = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
				        String endTimeStr = endTimeFormate.format(DateTimeFormatter.ofPattern("HH:mm"));
				        
				        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
				        int dayNumber = dayOfWeek.getValue();
				        
				        Optional<ShiftsWeekOffsModel> weekoffInfo =  shiftsWeekOffsRepository.isThisDayWeekOffByShiftIdDayNo(shiftId, dayNumber);
				        
				        if(shiftData.getHolidayCalId() != null && shiftData.getHolidayCalId() > 0) {
				        	Optional<Map<String, Object>> holidayInfo = holiayLinesRepository.isThisDayHolidayByHolidayCalIdAndDate(shiftData.getHolidayCalId(), fullDate);
				        	if(holidayInfo.isPresent() && holidayInfo.get().size() > 0) {
								wdmodel.setIsHoliday(EnumYesNo.Yes);
							}else {
								wdmodel.setIsHoliday(EnumYesNo.No);
							}				        	
				        }
						
						wdmodel.setShiftId(shiftId);
						wdmodel.setYearNo(yearNo);
						wdmodel.setMonthNo(monthNo);
						wdmodel.setOrgId(orgId);
						wdmodel.setDayNo(dayNo);
						wdmodel.setWorkingFullDate(localDate);
						wdmodel.setShiftStartTime(startTime);
						wdmodel.setShiftDuration(durationMinutes);
						wdmodel.setShiftEndTime(endTimeStr.toString());
						if(weekoffInfo.isPresent()) {
							wdmodel.setIsWeekOff(EnumYesNo.Yes);
						}else {
							wdmodel.setIsWeekOff(EnumYesNo.No);
						}
						wdmodel.setIsExemption(EnumYesNo.No);
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
