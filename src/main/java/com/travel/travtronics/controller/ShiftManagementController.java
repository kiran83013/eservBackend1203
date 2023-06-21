package com.travel.travtronics.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.BookingRequest;
import com.travel.travtronics.request.SearchUserDto;
import com.travel.travtronics.request.ShiftAssignmentRequest;
import com.travel.travtronics.request.ShiftSlotRelationMappedModel;
import com.travel.travtronics.request.ShiftsRequest;
import com.travel.travtronics.request.SlotShiftMappingRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.BreaksRequestResponseModel;
import com.travel.travtronics.response.HolidayRequestResponseModel;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.response.SlotRequestResponseModel;
import com.travel.travtronics.service.ShiftService;

@RestController
@Validated
public class ShiftManagementController {

	@Autowired
	ShiftService shiftService;

	@PostMapping("/add-breaks-template")
	public MessageStatusResponse createBreakTemplate(@RequestBody @Valid BreaksRequestResponseModel request) {
		return shiftService.createBreakTemplate(request);
	}

	@PutMapping("/modify-breaks-template")
	public MessageStatusResponse modifyBreakTemplate(@RequestBody @Valid BreaksRequestResponseModel request) {
		return shiftService.modifyBreakTemplate(request);
	}

	@GetMapping("/break-template/{id}")
	public APIResponse getBreakTemplateInfo(@PathVariable Long id) {
		return shiftService.getBreakTemplateInfo(id);
	}

	@GetMapping("/break-templates")
	public APIResponse getBreakTemplatesInfo(@RequestParam Long orgId) {
		return shiftService.getBreakTemplatesInfo(orgId);
	}

	@PostMapping("/add-slot-template")
	public MessageStatusResponse createSlotTemplate(@RequestBody @Valid SlotRequestResponseModel request) {
		return shiftService.createSlotTemplate(request);
	}

	@PutMapping("/modify-slot-template")
	public MessageStatusResponse modifySlotTemplate(@RequestBody @Valid SlotRequestResponseModel request) {
		return shiftService.modifySlotTemplate(request);
	}

	@GetMapping("/slot-template/{id}")
	public APIResponse getSlotTemplateInfo(@PathVariable Long id) {
		return shiftService.getSlotTemplateInfo(id);
	}

	@GetMapping("/slot-template-by-shift-id/{shiftId}")
	public APIResponse getSlotTemplateInfoByShiftId(@PathVariable Long shiftId) {
		return shiftService.getSlotTemplateInfoByShiftId(shiftId);
	}

	@GetMapping("/slot-templates")
	public APIResponse getSlotTemplatesInfo(@RequestParam Long orgId) {
		return shiftService.getSlotTemplatesInfo(orgId);
	}

	@PostMapping("/add-holiday-template")
	public MessageStatusResponse createHolidayTemplate(@RequestBody @Valid HolidayRequestResponseModel request) {
		return shiftService.createHolidayTemplate(request);
	}

	@PutMapping("/modify-holiday-template")
	public MessageStatusResponse modifyHolidayTemplate(@RequestBody @Valid HolidayRequestResponseModel request) {
		return shiftService.modifyHolidayTemplate(request);
	}

	@GetMapping("/holiday-template/{id}")
	public APIResponse getHolidayTemplateInfo(@PathVariable Long id) {
		return shiftService.getHolidayTemplateInfo(id);
	}

	@GetMapping("/holiday-templates")
	public APIResponse getHolidayTemplatesInfo(@RequestParam Long orgId) {
		return shiftService.getHolidayTemplatesInfo(orgId);
	}

	@PostMapping("/add-shifts-template")
	public MessageStatusResponse createShiftsTemplate(@RequestBody @Valid ShiftsRequest request) {
		return shiftService.createShiftsTemplateData(request);
	}

	@PutMapping("/update-shifts-template")
	public MessageStatusResponse updateShiftsTemplate(@RequestBody @Valid ShiftsRequest request) {
		return shiftService.updateShiftsTemplateData(request);
	}

	@GetMapping("/shifts-info/{shiftId}")
	public APIResponse getShiftsfInfoById(@PathVariable Long shiftId) {
		return shiftService.getShiftsfInfoDataById(shiftId);
	}

	@GetMapping("/all-shifts")
	public APIResponse getAllShiftsfInfo(@RequestParam Long orgId) {
		return shiftService.getAllShiftsfInfoData(orgId);
	}

	@PostMapping("/add-shifts-template-to-users")
	public MessageStatusResponse addShiftTemplateToUser(@RequestBody @Valid List<ShiftAssignmentRequest> request) {
		return shiftService.addShiftTemplateToUser(request);

	}

	@PutMapping("/modify-shifts-template-to-users")
	public MessageStatusResponse modifyShiftTemplateToUser(@RequestBody @Valid List<ShiftAssignmentRequest> request) {
		return shiftService.modifyShiftTemplateToUser(request);

	}

	@GetMapping("/shift-template-user/{id}")
	public APIResponse getUserShiftsfInfoUserById(@PathVariable Long id) {
		return shiftService.getUserShiftsfInfoUserById(id);
	}

	@PostMapping("/user-shift-slot-creation")
	public MessageStatusResponse UserShitftSlotCreation(@RequestBody @Valid SlotShiftMappingRequest request) {
		return shiftService.UserShitftSlotCreation(request);
	}

	@PutMapping("/user-shift-slot-updation")
	public MessageStatusResponse UserShitftSlotUpdation(@RequestBody @Valid SlotShiftMappingRequest request) {
		return shiftService.UserShitftSlotUpdation(request);
	}

	@GetMapping("/all-user-shift-slots")
	public APIResponse getAllUserShiftSlotfInfo(@RequestParam Long orgId) {
		return shiftService.getAllUserShiftSlotfInfo(orgId);
	}

	@GetMapping("/user-shift-slots/{id}")
	public APIResponse getUserSlotShiftsfInfoById(@PathVariable Long id) {
		return shiftService.getUserSlotShiftsfInfoById(id);
	}

	@GetMapping("/shift-template-user-info")
	public APIResponse getUserSlotShiftsfInfo(@RequestParam Long orgId) {
		return shiftService.getUserSlotShiftsfInfo(orgId);
	}

	@GetMapping("/user-shifts-working-days-list/{userId}/{monthNo}/{year}")
	public APIResponse getUserShiftWorkingDays(@PathVariable Long userId, @PathVariable Integer monthNo,
			@PathVariable Integer year) {
		return shiftService.getUserShiftWorkingDaysInfo(userId, monthNo, year);
	}

	@GetMapping("/user-shifts-slots-by-date/{userId}/{shiftId}/{date}")
	public APIResponse getUserShiftSlots(@PathVariable Long userId, @PathVariable Long shiftId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return shiftService.getUserShiftSlotsList(userId, shiftId, date);
	}

	@PutMapping("/modify-shift-slot-assignments")
	public MessageStatusResponse modifyShiftSlotAssignMents(@RequestBody @Valid ShiftSlotRelationMappedModel request) {
		return shiftService.modifyShiftSlotAssignMents(request);
	}

	@PostMapping("/book-appointment")
	public MessageStatusResponse bookAnAppointMent(@RequestBody @Valid BookingRequest request) {
		return shiftService.bookAnAppointMent(request);
	}

	@PutMapping("/modify-booked-appointment")
	public MessageStatusResponse modifyAnAppointMent(@RequestBody @Valid BookingRequest request) {
		return shiftService.modifyAnAppointMent(request);
	}

	@GetMapping("/appointment-info/{bookingId}")
	public APIResponse getAppointmentById(@PathVariable Long bookingId) {
		return shiftService.getAppointmentById(bookingId);
	}

	@GetMapping("/appointment-info")
	public APIResponse getAppointmentInfo(@RequestParam Long orgId) {
		return shiftService.getAppointmentInfo(orgId);
	}
	
	@PostMapping("/searchUserShifts")
	public ResponseEntity<?> searchUserShifts(@RequestBody SearchUserDto searchDto) {

		return shiftService.searchUserShifts(searchDto);
	}

}
