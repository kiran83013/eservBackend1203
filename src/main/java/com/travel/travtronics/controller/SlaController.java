package com.travel.travtronics.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.SlaHeaderRequestData;
import com.travel.travtronics.request.SlaLinesRequestData;
import com.travel.travtronics.request.SlaRequestData;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.SlaHeaderService;

@Validated
@RestController
public class SlaController {

	
	@Autowired
	SlaHeaderService slaHeaderService;	
	
	@PostMapping(value = "/sla-create", consumes = "application/json", produces = "application/json")
	public APIResponse createSlaData(@Valid @RequestBody SlaRequestData requestData) {
		return slaHeaderService.createSlaData(requestData);
	}
	
	@PutMapping(value = "/sla-update/{headerId}", consumes = "application/json", produces = "application/json")
	public APIResponse updateSlaData(@PathVariable Long headerId, @Valid @RequestBody SlaRequestData requestData) {
		return slaHeaderService.updateSlaData(headerId, requestData);
	}
	
	@GetMapping(value = "/sla-info-by-id/{headerId}", produces = "application/json")
	public APIResponse getSlaInfoById(@PathVariable Long headerId) {
		return slaHeaderService.getSlaInfoById(headerId);
	}
	
	@GetMapping(value = "/sla-info-by-orgid/{orgId}", produces = "application/json")
	public APIResponse getSlaInfoByOrgId(@PathVariable Long orgId) {
		return slaHeaderService.getSlaInfoByOrgId(orgId);
	}
	
	@GetMapping(value = "/sla-list")
	public APIResponsePaging getPagenationByOrganization(@RequestParam Integer orgId,
			@RequestParam(defaultValue = "0", required = false) int businessUnitId, 
			@RequestParam(defaultValue = "0", required = false) int costCenterId, 
			@RequestParam(defaultValue = "0", required = false) int locationId, 
			@RequestParam(defaultValue = "0") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return slaHeaderService.getPaginationByOrganization(orgId, businessUnitId, costCenterId, locationId, pageNo, pageSize, sortBy, sortType);
	}	
	
	
	@PostMapping(value = "/sla-create-header", consumes = "application/json", produces = "application/json")
	public APIResponse createSlaHeaderData(@Valid @RequestBody SlaHeaderRequestData requestData) {
		return slaHeaderService.createSlaHeaderData(requestData);
	}
	
	@PostMapping(value = "/sla-update-header/{headerId}", consumes = "application/json", produces = "application/json")
	public APIResponse updateSlaHeaderData(@PathVariable Long headerId, @Valid @RequestBody SlaHeaderRequestData requestData) {
		return slaHeaderService.updateSlaHeaderData(headerId, requestData);
	}
	
	@PostMapping(value = "/sla-create-lines/{headerId}", consumes = "application/json", produces = "application/json")
	public APIResponse createSlaHeaderLinesData(@PathVariable Long headerId, @Valid @RequestBody List<SlaLinesRequestData> requestData) {
		return slaHeaderService.createSlaHeaderLinesData(headerId, requestData);
	}
	
	
	/** ======================== Sla Lines =============================== 
	
	@PostMapping(value = "/sla-line-create", consumes = "application/json", produces = "application/json")
	public APIResponse createLine(@RequestBody List<SlaLinesModel> model) {
		return slaHeaderService.createLine(model);
	}
	
	@PutMapping(value = "/sla-line-update", consumes = "application/json", produces = "application/json")
	public APIResponse updateLine(@RequestBody List<SlaLinesModel> model) {
		return slaHeaderService.updateLine(model);
	}
	
	@GetMapping(value = "/sla-line-info/{id}", produces = "application/json")
	public APIResponse getByLineId(@PathVariable Long id) {
		return slaHeaderService.getByLineId(id);
	}
	
	@GetMapping(value = "/sla-lines-info-by-headerid/{headerId}", produces = "application/json")
	public APIResponse getByHeaderId(@PathVariable Long headerId) {
		return slaHeaderService.getByHeaderId(headerId);
	}
	
	@GetMapping(value = "/sla-lines-info-by-orgid/{orgId}", produces = "application/json")
	public APIResponse getByOrgLineId(@RequestParam Long orgId) {
		return slaHeaderService.getByOrgLineId(orgId);
	}
	
	
	@GetMapping(value = "/sla-lines-list")
	public APIResponsePaging getPagenationByLineOrganization(@RequestParam Long orgId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return slaHeaderService.getPagenationByLineOrganization(orgId, pageNo, pageSize, sortBy, sortType);
	}
	
	**/
	
	
	
	
}
