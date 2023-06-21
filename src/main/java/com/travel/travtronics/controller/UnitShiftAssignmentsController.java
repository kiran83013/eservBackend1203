package com.travel.travtronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.SearchUnitDto;
import com.travel.travtronics.request.UnitSearchDto;
import com.travel.travtronics.request.UnitShiftAssignmentRequest;
import com.travel.travtronics.request.UnitShiftSlotRelationMappedModel;
import com.travel.travtronics.request.UnitSlotShiftMappingRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.UnitShiftAssignmentsService;

@RestController
@Validated
public class UnitShiftAssignmentsController {

	@Autowired
	UnitShiftAssignmentsService unitshiftassignmentsService;

	@PostMapping("/add-unit-shifts-template-to-unit")
	public MessageStatusResponse addShiftTemplateToUnit(@RequestBody List<UnitShiftAssignmentRequest> request) {
		return unitshiftassignmentsService.addShiftTemplateToUnit(request);

	}

	@PutMapping("/modify-unit-shifts-template-to-unit")
	public MessageStatusResponse modifyShiftTemplateToUnit(@RequestBody List<UnitShiftAssignmentRequest> request) {
		return unitshiftassignmentsService.modifyShiftTemplateToUnit(request);

	}

	@GetMapping("/unit-shift-template-unit/{id}")
	public APIResponse getUnitShiftsfInfoUnitById(@PathVariable Long id) {
		return unitshiftassignmentsService.getUnitShiftsfInfoUnitById(id);
	}

	@PostMapping("/unit-shift-slot-creation")
	public MessageStatusResponse UnitShitftSlotCreation(@RequestBody UnitSlotShiftMappingRequest request) {
		return unitshiftassignmentsService.UnitShitftSlotCreation(request);
	}

	@PutMapping("/unit-shift-slot-updation")
	public MessageStatusResponse UnitShitftSlotUpdation(@RequestBody UnitSlotShiftMappingRequest request) {
		return unitshiftassignmentsService.UnitShitftSlotUpdation(request);
	}

	@GetMapping("/all-unit-shift-slots")
	public APIResponse getAllUnitShiftSlotfInfo(@RequestParam Long orgId) {
		return unitshiftassignmentsService.getAllUnitShiftSlotfInfo(orgId);
	}

	@GetMapping("/unit-shift-slots/{id}")
	public APIResponse getUnitSlotShiftsfInfoById(@PathVariable Long id) {
		return unitshiftassignmentsService.getUnitSlotShiftsfInfoById(id);
	}

	@GetMapping("/shift-template-unit-info")
	public APIResponse getUnitSlotShiftsfInfo(@RequestParam Long orgId) {
		return unitshiftassignmentsService.getUnitSlotShiftsfInfo(orgId);
	}

	@PostMapping("/unit-search-shifts")
	public ResponseEntity<?> searchUnitShifts(@RequestBody UnitSearchDto searchDto) {

		return unitshiftassignmentsService.searchUnitShifts(searchDto);
	}

	@PutMapping("/modify-shift-and-shiftslot-assignments")
	public MessageStatusResponse modifyShiftSlotAssignMents(@RequestBody UnitShiftSlotRelationMappedModel request) {
		return unitshiftassignmentsService.modifyShiftSlotAssignMents(request);
	}

	@PostMapping("/search-unit")
	public ResponseEntity<?> searchUnit(@RequestBody SearchUnitDto searchDto) {

		return unitshiftassignmentsService.searchUnit(searchDto);
	}

	@GetMapping(value = "/list-unitshiftslotsearch-pagination")
	public APIResponsePaging getUnitShiftSlotPagenationByOrganization(@RequestParam(required = false) Long orgId,
			@RequestParam(required = false) Long shiftId, @RequestParam(required = false) Long slotTemplateId,
			@RequestParam(required = false) Long unitId, @RequestParam(required = false) String unitName,@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return unitshiftassignmentsService.getUnitShiftSlotPagenationByOrganization(orgId, shiftId, slotTemplateId,
				unitId,unitName, pageNo, pageSize, sortBy, sortType);
	}

}
