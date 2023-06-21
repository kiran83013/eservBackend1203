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
import com.travel.travtronics.eserv.model.SanctionGroup;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.SanctionGroupService;

@RestController
@RequestMapping("/sanctiongroup")
public class SanctionGroupController {
	
	
	@Autowired
	SanctionGroupService sanctionGroupService;
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody SanctionGroup model) {
		return sanctionGroupService.create(model);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public APIResponse update(@RequestBody SanctionGroup model) {
		return sanctionGroupService.update(model);
	}
	
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getById(@RequestParam Long id) {
		return sanctionGroupService.getById(id);
	}
	
	@GetMapping(value = "orgId", produces = "application/json")
	public APIResponse getByOrgId(@RequestParam Long orgId) {
		return sanctionGroupService.getByOrgId(orgId);
	}
	
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam Long orgId, @RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return sanctionGroupService.getPaginationByOrganization(orgId, name, pageNo, pageSize, sortBy, sortType);
	}
	
	

}
