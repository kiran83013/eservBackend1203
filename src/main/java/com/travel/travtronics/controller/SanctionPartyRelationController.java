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
import com.travel.travtronics.eserv.model.SanctionPartyRelation;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.SanctionPartyRelationService;

@RestController
@RequestMapping("/spr")
public class SanctionPartyRelationController {

	
	@Autowired
	SanctionPartyRelationService sanctionPartyRelationService;
	
	@PostMapping(value = "create", consumes = "application/json", produces = "application/json")
	public APIResponse create(@RequestBody SanctionPartyRelation model) {
		return sanctionPartyRelationService.create(model);
	}
	
	@PutMapping(value = "update", consumes = "application/json", produces = "application/json")
	public APIResponse update(@RequestBody SanctionPartyRelation model) {
		return sanctionPartyRelationService.update(model);
	}
	
	@GetMapping(value = "id", produces = "application/json")
	public APIResponse getById(@RequestParam Long id) {
		return sanctionPartyRelationService.getById(id);
	}
	
	@GetMapping(value = "orgId", produces = "application/json")
	public APIResponse getByOrgId(@RequestParam Long orgId) {
		return sanctionPartyRelationService.getByOrgId(orgId);
	}
	
	
	@GetMapping(value = "/list-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam Long orgId,@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return sanctionPartyRelationService.getPaginationByOrganization(orgId, name, pageNo, pageSize, sortBy, sortType);
	}
}
