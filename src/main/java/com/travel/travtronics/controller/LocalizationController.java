package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Localization;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.LocalizationService;

@RestController
@RequestMapping("localization")
public class LocalizationController {

	@Autowired
	LocalizationService localizationService;
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody Localization model) {
		return localizationService.create(model);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public APIResponse update(@RequestBody Localization model) {
		return localizationService.update(model);
	}
	
	@GetMapping(value = "findById", produces = "application/json")
	public APIResponse findById(@RequestParam Long id) {
		return localizationService.findById(id);
		
	}
	
	@GetMapping(value = "findAll", produces = "application/json")
	public APIResponse findAll() {
		return localizationService.findAll();
	}
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return localizationService.getPaginationByOrganization(organizationId, pageNo, pageSize, sortBy, sortType);
	}
	
	@GetMapping(value = "findByRoleTypeId", produces = "application/json")
	public APIResponse findByRoleTypeId(@RequestParam Long roleTypeId) {
		return localizationService.findByRoleTypeId(roleTypeId);
		
	}
	
	@GetMapping(value = "getAllRoleType", produces = "application/json")
	public APIResponse findAllRoleType() {
		return localizationService.findAllRoleType();	
	}
	
	@GetMapping(value = "findAllRoles", produces = "application/json")
	public APIResponse findAllRoles(@RequestParam Long roleTypeId ) {
		return localizationService.findAllRoles(roleTypeId);	
	}
}
